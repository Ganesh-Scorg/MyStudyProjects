package org.gs;

import java.util.HashMap;

import org.json.JSONObject;

/*

We have some data here, somewhere in this block of bytes is some TLV data. 
Please write some code that will take a block like this and render the TLV data as JSON.


You're welcome to reference https://emvlab.org/emvtags/
Or any other materials.

02 59 4D 1E 01 12 53 52 00 02 01 0C 50 0B 56 49 53 41 20 43 52 45 44 49 54 57 10 44 47 96 FF FF FF 31 64 D2 20 12 01 FF FF FF FF 82 02 18 00 84 07 A0 00 00 00 03 10 10 87 01 00 95 05 80 80 00 80 00 4F 07 A0 00 00 00 03 10 10 5F 20 0C 54 48 41 4F 2F 4F 52 4C 41 4E 44 4F 5F 24 03 22 01 31 5F 2A 02 08 40 5F 28 02 08 40 5F 30 02 02 01 5F 34 01 02 5F 2D 02 65 6E 9A 03 19 03 21 9B 02 68 00 9C 01 00 9F 02 06 00 00 00 00 00 01 9F 03 06 00 00 00 00 00 00 9F 06 07 A0 00 00 00 03 10 10 9F 07 02 FF 00 9F 08 02 00 96 9F 09 02 00 8C 9F 0D 05 FC 50 AC 88 00 9F 0E 05 00 00 00 00 00 9F 0F 05 FC 70 BC 98 00 9F 10 07 06 0F 0A 03 A0 A0 00 9F 1A 02 08 40 9F 21 03 09 51 43 9F 26 08 9A B3 4E 50 96 C7 F3 25 9F 27 01 80 9F 33 03 E0 F8 C8 9F 34 03 5E 00 00 9F 35 01 22 9F 36 02 00 09 9F 37 04 58 A8 FF 33 9F 3C 02 48 00 9F 40 05 F0 00 E0 A0 01 9F 53 01 46 03 E4 

*/

/*
 * TLV Rules - 0011 1111 ->
 *   
 * 
 */

public class TLVToJSON
{
	private static HashMap<String, String> TLVTags = new HashMap<>();

	//To track the TLV tag who has value as TLV
	private static HashMap<String, String> ConstructedTLVTag = new HashMap<>(); 

	//Note, JSONObject need external jar to be imported org.json-chargebee-1.0.jar
	private static JSONObject tlvJson;
	
	public static void main(String[] args)
	{
		String TLVDataSample1 = "820218008407A0000000031010870100950580800080004F07A00000000310105F200C5448414F2F4F524C414E444F5F24032201315F2A0208405F280208405F300202015F3401025F2D02656E9A031903219B0268009C01009F02060000000000019F03060000000000009F0607A00000000310109F0702FF009F080200969F0902008C9F0D05FC50AC88009F0E0500000000009F0F05FC70BC98009F1007060F0A03A0A0009F1A0208409F21030951439F26089AB34E5096C7F3259F2701809F3303E0F8C89F34035E00009F3501229F360200099F370458A8FF339F3C0248009F4005F000E0A0019F530146"; 
		String TLVDataSample2 = "6F35820218008407A0000000031010870100950580800080004F07A00000000310105F200C5448414F2F4F524C414E444F5F2403220131";
		constructTLVData(TLVDataSample2);
		
		System.out.println("Constructed Tag List"+getConstructedTLVTag());
		
		System.out.println("Primitive Tag List"+getTLVTags());
		
		tlvJson = new JSONObject(getTLVTags());
		
		System.out.println("TLV Data in JSON Format"+getTlvJson());
	}
	
	public static void constructTLVData(String TLVData)
	{
		int tlvdatalen = TLVData.length();
		
		byte hexarray[] = hexStringToByteArray(TLVData);
		
		for (int i = 0, j = 0; i < hexarray.length && j<tlvdatalen;)
		{
			String tag = null;
			int len = 0;
			String value = null;
			boolean isconstructedtag = false;
			
			if ((hexarray[i] & 0x20) == 0x20) // if sixth bit of tag is 1
			{
				System.out.println("Its a constructed tag who's value will have TLV format");
				isconstructedtag = true;
			}
			if ((hexarray[i] & 0x1F) == 0x1F) // if lower 5 bits of tag is 1
			{
				tag = (String)TLVData.substring(j, j + 4);
				
				j = j + 4;
				i = i + 2;
			}
			else
			{
				tag = (String)TLVData.substring(j, j + 2);
				j = j + 2;
				i++;
			}
			System.out.println("Tag = " + tag);
			
			if ((hexarray[i] & 0x80) == 0x80) // if 8th bit is 1 -> remaining 7 bits says the length of
														 // length but lets consider max FF
			{
				i++; // Skip the length of length part
				j = j + 2;
			}
		
			len = hexarray[i];
			i++; 
			j = j + 2;
			
			value = (String)TLVData.substring(j, j + len * 2);
			System.out.println("Value = " + value);
			j = j + len * 2;
			i = i + len;
			
			if(isconstructedtag)
			{
				ConstructedTLVTag.put(tag, value);
				//IN case Value contains TLV, parse it again recursively
				constructTLVData(value);
			}
			else
			{
				TLVTags.put(tag, value);
			}
			
		}
		
	}
	
	public static byte[] hexStringToByteArray(String tlvstring)
	{
		int len = tlvstring.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2)
		{
			data[i / 2] =
				(byte)((Character.digit(tlvstring.charAt(i), 16) << 4) + Character.digit(tlvstring.charAt(i + 1), 16));
		}
		return data;
	}

	public static HashMap<String, String> getTLVTags()
	{
		return TLVTags;
	}

	public static HashMap<String, String> getConstructedTLVTag()
	{
		return ConstructedTLVTag;
	}

	public static JSONObject getTlvJson()
	{
		return tlvJson;
	}
	
}

package com.example.androidproject;

import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class UserPullParser {
	//<user>zhang</user>
	public String parser(InputStream is)
	{
		try {
			XmlPullParser parser=XmlPullParserFactory.newInstance().newPullParser();
		parser.setInput(is, "utf-8");
			int eventType=parser.getEventType();
			while(eventType!=parser.END_DOCUMENT)
			{
				if (eventType==parser.START_TAG)
				{
					String tagName=parser.getName();
					if ("user".equals(tagName))
					{
						return parser.nextText().trim();
					}
				}
				eventType=parser.next();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}

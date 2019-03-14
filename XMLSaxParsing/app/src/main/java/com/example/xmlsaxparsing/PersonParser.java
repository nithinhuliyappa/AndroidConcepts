package com.example.xmlsaxparsing;

import android.util.Log;
import android.util.Xml;
import android.widget.Switch;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class PersonParser {

    public static class PersonSAXParser extends DefaultHandler{
        ArrayList<Person> persons;
        Person person;
        Address address;
        StringBuilder innerXml;

        static public ArrayList<Person> parsePersons(InputStream inputStream) throws IOException, SAXException {
            PersonSAXParser parser = new PersonSAXParser();
            Xml.parse(inputStream, Xml.Encoding.UTF_8, parser);
            return parser.persons;
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            this.persons = new ArrayList<>();
            this.innerXml = new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if (localName.equals("person")){
                person = new Person();
                person.id = Long.valueOf(attributes.getValue("id"));
            }else if (localName.equals("address")){
                address = new Address();
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);

            String text = "";
            if (innerXml.toString() != null){
                text = innerXml.toString().trim();
            }
            if (localName.equals("name")){
                person.name = text;
                //Log.d("demo", text);
            }else if (localName.equals("age")){
                person.age = Integer.parseInt(text);
            }else if (localName.equals("line1")){
                address.line1 = text;
            }else if (localName.equals("city")){
                address.city = text;
            }else if (localName.equals("zip")){
                address.zip = text;
            }else if (localName.equals("state")){
                address.state = text;
            }else if (localName.equals("address")){
                person.address = address;
            }else if(localName.equals("person")){
                persons.add(person);
            }

            innerXml.setLength(0);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            innerXml.append(ch, start, length);
        }
    }

    public static class PersonPullParser {
        static public ArrayList<Person> parsePersons(InputStream inputStream) throws XmlPullParserException, IOException {
            ArrayList<Person> persons = new ArrayList<>();
            Person person = null;
            Address address = null;
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(inputStream, "UTF-8");

            int event = parser.getEventType();

            while (event !=XmlPullParser.END_DOCUMENT){

                switch (event){

                    case XmlPullParser.START_TAG:

                        if(parser.getName().equals("person")){

                            person = new Person();
                            person.id = Long.valueOf(parser.getAttributeValue(null, "id"));
                        } else  if(parser.getName().equals("name")){
                            person.name = parser.nextText().trim();
                        } else if(parser.getName().equals("age")){
                            person.age = Integer.valueOf(parser.nextText().trim());
                        } else if(parser.getName().equals("address")){
                            address = new Address();
                        } else if(parser.getName().equals("line1")){
                            address.line1 = parser.nextText().trim();
                        } else if(parser.getName().equals("city")){
                            address.city = parser.nextText().trim();
                        } else  if(parser.getName().equals("state")){
                            address.state = parser.nextText().trim();
                        } else if (parser.getName().equals("zip")){
                            address.zip = parser.nextText().trim();
                        }
                    break;

                    case XmlPullParser.END_TAG:

                        if (parser.getName().equals("person")){
                            persons.add(person);
                        }else if (parser.getName().equals("address")){
                            person.address = address;
                        }
                    break;

                    default:

                    break;

                }
                event = parser.next();
            }
            return  persons;
        }
    }
}

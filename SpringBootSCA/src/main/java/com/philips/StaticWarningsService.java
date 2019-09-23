/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
@Service
public class StaticWarningsService {
  public int parseXML(String projectname, String currentdir) throws Exception {
    DocumentBuilderFactory dbFactory;
    DocumentBuilder dBuilder;
    Document doc;
    NodeList nList = null;

    final File fXmlFile = new File(currentdir + "\\Pmdreports\\" + projectname + ".xml");
    if (fXmlFile.exists()) {
      dbFactory = DocumentBuilderFactory.newInstance();
      dBuilder = dbFactory.newDocumentBuilder();
      doc = dBuilder.parse(fXmlFile);
      nList = doc.getElementsByTagName("file");
    }


    return getNoOfWarnings(nList);
  }


  public int getNoOfWarnings(NodeList nList) {
    int noofissues = 0;
    for (int temp = 0; temp < nList.getLength(); temp++) {
      final Node nNode = nList.item(temp);
      if (nNode.getNodeType() == Node.ELEMENT_NODE) {
        final Element eElement = (Element) nNode;
        final NodeList violations = eElement.getElementsByTagName("violation");
        noofissues += violations.getLength();

      }
    }
    return noofissues;
  }

}

/*
 *  Copyright 2007-2008, Plutext Pty Ltd.
 *   
 *  This file is part of docx4j.

    docx4j is licensed under the Apache License, Version 2.0 (the "License"); 
    you may not use this file except in compliance with the License. 

    You may obtain a copy of the License at 

        http://www.apache.org/licenses/LICENSE-2.0 

    Unless required by applicable law or agreed to in writing, software 
    distributed under the License is distributed on an "AS IS" BASIS, 
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
    See the License for the specific language governing permissions and 
    limitations under the License.

 */

package org.pptx4j.samples;


import org.apache.log4j.Logger;
import org.docx4j.XmlUtils;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.PresentationMLPackage;
import org.docx4j.openpackaging.parts.PartName;
import org.docx4j.openpackaging.parts.PresentationML.SlidePart;
import org.pptx4j.pml.CTShape;



/**
 * @author jharrop
 *
 */
public class CreateHelloWorld  {
	
	protected static Logger log = Logger.getLogger(CreateHelloWorld.class);
		
	public static void main(String[] args) throws Exception {

		// Where will we save our new .ppxt?
		String outputfilepath = System.getProperty("user.dir") + "/sample-docs/pptx-test.pptx";
		
		// Create skeletal package
		PresentationMLPackage presentationMLPackage = PresentationMLPackage.createPackage(); 
		
		// It contains a first slide; get it ..
		// TODO - add convenience methods?
		SlidePart slidePart = (SlidePart)presentationMLPackage.getParts().getParts().get(
				new PartName("/ppt/slides/slide1.xml"));
		
		// Create and add shape
		CTShape sample = ((CTShape)XmlUtils.unmarshalString(SAMPLE_SHAPE, Context.jcPML) );
		slidePart.getJaxbElement().getCSld().getSpTree().getSpOrGrpSpOrGraphicFrame().add(sample);
		
		// All done: save it
		presentationMLPackage.save(new java.io.File(outputfilepath));

		System.out.println("\n\n done .. saved " + outputfilepath);
		
	}	
	
	
	private static String SAMPLE_SHAPE = 			
		"<p:sp   xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" xmlns:p=\"http://schemas.openxmlformats.org/presentationml/2006/main\">"
		+ "<p:nvSpPr>"
		+ "<p:cNvPr id=\"4\" name=\"Title 3\" />"
		+ "<p:cNvSpPr>"
			+ "<a:spLocks noGrp=\"1\" />"
		+ "</p:cNvSpPr>"
		+ "<p:nvPr>"
			+ "<p:ph type=\"title\" />"
		+ "</p:nvPr>"
	+ "</p:nvSpPr>"
	+ "<p:spPr />"
	+ "<p:txBody>"
		+ "<a:bodyPr />"
		+ "<a:lstStyle />"
		+ "<a:p>"
			+ "<a:r>"
				+ "<a:rPr lang=\"en-US\" smtClean=\"0\" />"
				+ "<a:t>Hello World</a:t>"
			+ "</a:r>"
			+ "<a:endParaRPr lang=\"en-US\" />"
		+ "</a:p>"
	+ "</p:txBody>"
+ "</p:sp>";

	
}

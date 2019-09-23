<?xml version="1.0"?>

<!--
 *  Cyvis is a free metric tool for the Java(tm) platform
 *   
 *  Copyright (C) 2005-2006  Pradeep Selvaraj, Vinay Iyer
 *  
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *  
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *  
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *     
 *-->
 <!-- Written by Pradeep Selvaraj-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="1.0">
 
    <xsl:output method="html"/>
    <xsl:template match="/">
    <xsl:variable name="packages" 
                      select="Project/classes/Class[not(packageName=preceding-sibling::Class/packageName)]/packageName"/>
	    <HTML>
			    <HEAD>
			    <STYLE>
			    .body 
{
  font-family: arial, helvetica; 
  font-size: 12px;
}
.head 
{
  font-size : 25px;
  font-weight : bold;
}
.subhead 
{
  font-size : 21px;
  font-weight : bold;
}
.tableheader 
{ 
  background-color:#FDE7C3;
  font-size : 15px;
  font-weight : bold;
  vertical-align:top;
}
.ClassTableHeader 
{ 
  background-color:#D46139;
  font-size : 15px;
  font-weight : bold;
  vertical-align:top;
}
.tablecell 
{
  font-size : 12px;
  vertical-align:top;
}
			    </STYLE>
				
                 <TITLE>Cyvis Report</TITLE>
            </HEAD>
            <BODY bgcolor="white" align ="center" class="body" >
                <div align="center" class="head">Cyvis Report</div>
                <div align="center">
                <a href="http://cyvis.sorceforge.net" class="site" >cyvis.sourceforge.net</a>
                </div>
                <div align="center">Report generated at <xsl:value-of select="$timestamp"/>.
                </div>
                <BR/>
 <!--===================Project Summary==============================-->
			   <div class="subhead" align="left">Project Summary</div>
			     <TABLE  border="1" width="90%">
                    <TR>
                    <TH class="tableheader" width="50%">Metric</TH>
                    <TH class="tableheader" width="50%">Value</TH>
                   </TR>
                   <TR>
                   <TD width="50%"> Total Number of Packages </TD>
                   <TD align="center"><xsl:value-of select="count($packages)"/></TD>
                   </TR>
 				   <TR>
                   <TD width="50%"> Total Number of Classes </TD>
                   <TD align="center"><xsl:value-of select="count(//Class[size>0])"/></TD>
                   </TR>
    			   <TR>
                   <TD width="50%"> Total Number of Interfaces </TD>
                   <TD align="center"><xsl:value-of select="count(//Class[size=0])"/></TD>
                   </TR>
    			   <TR>
                   <TD width="50%"> Total Number of Methods</TD>
                   <TD align="center"><xsl:value-of select="count(//Method)"/></TD>
                   </TR>
    
                </TABLE>
<br/><br/>
<!--===================End of Project Summary==============================-->

<!--===================List of Packages==============================-->
                <div align="left" class="subhead">List of Packages</div>
                  <TABLE  border="1" width="90%" >
                    <TR>
                    <TH class="tableheader" width="60%">Package Name</TH>
                    <TH class="tableheader" width="40%">Total Number of Classes</TH>
                    </TR>
                    <xsl:for-each select="$packages">
                        <TR>                           
                           <TD width="60%" class="tablecell" ><a href="#{.}"><xsl:value-of select="."/></a> </TD>
                           <xsl:variable name="package" select="."/>
                           <TD width="40%" align ="center" class="tablecell" ><xsl:value-of select="count(//Class[packageName=$package])"/> </TD>
                        </TR>
                    </xsl:for-each>
                   </TABLE>
                 <br/><br/>
<!--===================End of Packages list==============================-->     

<!--===================List of classes==============================-->            
                 <xsl:for-each select="$packages">
		                   <div align="left" class="subhead"><A name="{.}">Classes in package: <xsl:value-of select="."/></A></div>
		                   <TABLE  border="1" width="90%" >
		                     <TR>
		                     <TH class="tableheader" width="50%">Class Name</TH>
		                     <TH class="tableheader" width="25%">Total Number of Methods</TH>
		                     <TH class="tableheader" width="25%">Instruction Count</TH>
		                     </TR>
		                     <xsl:variable name="package" select="."/>
		                     <xsl:variable  name="classes" select="//Class[packageName=$package]"/>
		                     <xsl:for-each select="$classes">     
		                          <TR>
		                            <TD width="50%" class="tablecell" ><a href="#{$package}.{className}"><xsl:value-of select="className"/></a> </TD>
		                            <TD width="25%" align ="center" class="tablecell" >
		 							  <xsl:value-of select="count(methods/Method)"/> 
		                            </TD>
		                            <TD width="25%" align ="center" class="tablecell" >
		                              <xsl:value-of select="size"/> 
		                           </TD>
		                         </TR>
		                     </xsl:for-each>
		                   </TABLE>
		                   <BR/><BR/>
                 </xsl:for-each>
                 <br/><br/>
 <!--===================End of classes list==============================-->


<!--===================List of Methods==============================-->                 
                   <xsl:for-each select="$packages">
		                    <xsl:variable name="package" select="."/>
		                    <xsl:variable  name="classes" select="//Class[packageName=$package]"/>
		                    <xsl:for-each select="$classes">     
		                    <div align="left" class="subhead">
		                    <BR/><BR/>
		                    <A name="{$package}.{className}">Methods in Class: 
		  					<xsl:value-of select="$package"/>.<xsl:value-of select="className"/></A>
		                    </div>
		                    <TABLE  border="1" width="90%" >
		                      <TR>
		                      <TH class="tableheader" width="50%">Method Name</TH>
		                      <TH class="tableheader" width="25%">Cyclomatic Complexity</TH>
		                      <TH class="tableheader" width="25%">Length</TH>
		                      </TR>
		  						<xsl:for-each select=".//Method">     
		                           <TR>
		                             <TD width="50%" class="tablecell" ><xsl:value-of select="name"/></TD>
		                             <TD width="25%" align ="center" class="tablecell" >
		  							  <xsl:value-of select="cyclomaticComplexity"/> 
		                             </TD>
		                             <TD width="25%" align ="center" class="tablecell" >
		                             <xsl:value-of select="length"/>
		                             </TD>
		                          </TR>
		                      </xsl:for-each>
		                    </TABLE>
		                    </xsl:for-each>
		                    <BR/>
                 </xsl:for-each>
                 
 <!--===================End of methods==============================-->
	           </BODY>
        </HTML>
    </xsl:template>

</xsl:stylesheet>
 
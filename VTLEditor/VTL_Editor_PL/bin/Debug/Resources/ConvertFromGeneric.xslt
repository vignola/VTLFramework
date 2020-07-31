<?xml version="1.0"?>
<xslt:stylesheet version="1.0" xmlns:data="http://www.SDMX.org/resources/SDMXML/schemas/v2_0/generic" 
                 xmlns:xslt="http://www.w3.org/1999/XSL/Transform" 
                 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
                 xmlns:message="http://www.SDMX.org/resources/SDMXML/schemas/v2_0/message">
  <xslt:param name="sep">|</xslt:param>

  <xslt:output method="text"/>
  <xslt:template match="message:MessageGroup">
    <xslt:for-each select="data:DataSet">
      <!--<xslt:value-of select="data:KeyFamilyRef"/>
			<xslt:text>&#xa;</xslt:text>-->
      <xslt:for-each select="data:Series">
        <xslt:variable name="key">
          <xslt:for-each select="data:SeriesKey">
            <xslt:for-each select="data:Value">
              <xslt:value-of select="@value"/>
              <xslt:value-of select="$sep" />
            </xslt:for-each>
          </xslt:for-each>
        </xslt:variable>
        <xslt:for-each select="data:Obs">
          <xslt:value-of select="$key"/>
          <xslt:value-of select="data:Time"/>
          <xslt:value-of select="$sep" />
          <xslt:value-of select="data:ObsValue/@value"/>
          <xslt:text>&#xa;</xslt:text>
        </xslt:for-each>
      </xslt:for-each>
    </xslt:for-each>
  </xslt:template>

  <xslt:template match="message:GenericData">
    <xslt:for-each select="message:DataSet">
      <!--<xslt:value-of select="data:KeyFamilyRef"/>
			<xslt:text>&lt;br&gt;</xslt:text>-->
      <xslt:for-each select="data:Series">
        <xslt:if test="position() = 1">
          <xslt:for-each select="data:SeriesKey">
            <xslt:for-each select="data:Value">
              <xslt:value-of select="@concept"/>
              <xslt:value-of select="$sep" />
            </xslt:for-each>
          </xslt:for-each>
          <!--<xslt:text>TIME|OBSVALUE&lt;br&gt;</xslt:text>-->
          <xslt:text>TIME|OBSVALUE&#xa;</xslt:text>
        </xslt:if>
        <xslt:variable name="key">
          <xslt:for-each select="data:SeriesKey">
            <xslt:for-each select="data:Value">
              <xslt:value-of select="@value"/>
              <xslt:value-of select="$sep" />
            </xslt:for-each>
          </xslt:for-each>
        </xslt:variable>
        <xslt:for-each select="data:Obs">
          <xslt:value-of select="$key"/>
          <xslt:value-of select="data:Time"/>
          <xslt:value-of select="$sep" />
          <xslt:value-of select="data:ObsValue/@value"/>
          <xslt:text>&#xa;</xslt:text>
          <!--<xslt:text>&lt;br&gt;</xslt:text>-->
        </xslt:for-each>
      </xslt:for-each>
    </xslt:for-each>
  </xslt:template>

</xslt:stylesheet>
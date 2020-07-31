// -----------------------------------------------------------------------
// <copyright file="ResponseUtils.cs" company="Microsoft">
// TODO: Update copyright text.
// </copyright>
// -----------------------------------------------------------------------

namespace WebServiceConnector
{
    using System.CodeDom.Compiler;
    using System.IO;
    using System.Text;
    using System.Xml;

    /// <summary>
    /// TODO: Update summary.
    /// </summary>
    public static class ResponseUtils
    {

        public static string FormatXmlString(string xmlString, XmlReaderSettings xmlReaderSettings)
        {
            var stringBuilder = new StringBuilder();
            using (XmlReader reader = XmlReader.Create(new StringReader(xmlString), xmlReaderSettings))
            {
                //var settings = new XmlWriterSettings { OmitXmlDeclaration = true, Indent = true, IndentChars = "\t", Encoding = Encoding.UTF8 };
                using (var xmlWriter = new XmlTextWriter(new IndentedTextWriter(new StringWriter(stringBuilder))))
                {
                    FormatXml(reader, xmlWriter);
                }
            }

            return stringBuilder.ToString();
        }

        public static void FormatXmlFile(string inputPath, string outputPath)
        {
            using (XmlReader reader = XmlReader.Create(inputPath))
            {
                using (var fs = new FileStream(outputPath, FileMode.Create))
                {
                    //var settings = new XmlWriterSettings { OmitXmlDeclaration = true, Indent = true, IndentChars = "\t", Encoding = Encoding.UTF8 };
                    using (var xmlWritter = new XmlTextWriter(fs, Encoding.UTF8))
                    {
                        FormatXml(reader, xmlWritter);
                    }
                }
            }
        }

        private static void FormatXml(XmlReader reader, XmlTextWriter xmlWriter)
        {
            if (xmlWriter == null || reader == null)
                return;

            xmlWriter.Formatting = Formatting.Indented;
            xmlWriter.WriteStartDocument();
            reader.MoveToContent();
            try
            {
                while (!reader.EOF)
                {
                    xmlWriter.WriteNode(reader, true);
                }
            }
            catch (XmlException exception)
            {
                xmlWriter.WriteEndDocument();
            }

        }
    }
}


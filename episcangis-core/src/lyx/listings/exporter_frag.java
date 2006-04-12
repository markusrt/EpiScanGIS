XmlExporter xmlExporter = new XmlExporter();
map.export(xmlExporter);
xmlInputStream = xmlExporter.getInputStream();
contentType = xmlExporter.getContentType();

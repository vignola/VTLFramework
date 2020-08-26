&quot;The [VTL (Validation and Transformation Language)](https://sdmx.org/?page_id=5096) task force was set up early in 2013, composed of members of SDMX, DDI and GSIM communities and the work started in summer 2013. The intention was to provide a language usable by statisticians to express logical validation rules and transformations on data, described as either dimensional tables or unit-record data. The assumption is that this logical formalization of validation and transformation rules could be converted into specific programming languages for execution (SAS, R, Java, SQL, etc.), and would provide at the same time, a &quot;neutral&quot; business-level expression of the processing taking place, against which various implementations can be mapped. Experience with existing examples suggests that this goal would be attainable.&quot; (_VTL – version 2.0 , User manual_)

**Why do we need a standard language to validate and transform statistical data?**

**The enrichment of the statistics** : rules indeed, represent metadata of process and are crucial for the information assets of a Satistical Office. Knowing how data have been treated before the dissemination can give an added value to the understandability of data and also of the statistical process itself.

**The reuse of rules:** when rules are defined using a common language in a metadata system it is more easy to visualize the internal validation and transformation approaches of different statistical domains, this can facilitate the sharing and reusing of rules and simplify the statistician work.

**The traceability of data:** through the validation and transformation rules is possible to have a clear vision on how data have been changed during the statistical process and to move back from aggregated data to microdata.

**The standardization:** sharing information with a common language are the fundamental keys for standardization of rules internally the statistical office.

 ![](RackMultipart20200729-4-151tw08_html_d01ed521f0712cce.gif)

In order to support the use of VTL, Italian Institute of Statistics, thanks to Eurostat&#39;s Grant project on Validation Grant, decided to develop the VTL Framework aimed to help the statisticians to create VTL rules and to convert them into an executable language.

VTL Framework is composed of four parts:

- **VTLCompiler** (JAVA Spring boot Web service) the web service that represents the core of the framework. It is a REST web service that allows the user to validate syntactically, semantically the VTL statements and to perform the synthesis of VTL to an executable language: SQL

  At the present time it can translate VTL in four SQL dialects: Oracle SQL, T-SQL, MySQL and Postgresql

- **VTLEditor** (C# Windows Desktop application) Graphical user interface to interact with the elements of the infrastructure

- **VTLInt\_WS** (C# Windows Communication Foundation webservice) to manage the exchanges between the user, the VTL DB and the VTLCompiler.

- **VTLDB** (Oracle DB) to store the structural metadata using the VTL-IM

## Usage and documentation

Usage info, documentation are available at the
[project site](https://vtlframeworkdevelopment.github.io/VTLFramework/).

![](RackMultipart20200729-4-151tw08_html_dfedecc1f599f215.png)

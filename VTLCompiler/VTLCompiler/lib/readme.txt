Installa la libreria nel repository maven per avviare il progetto
mvn install:install-file
    -Dfile=C:\Users\darossi\IdeaProjects\VtlCompiler\Vtl-Api\src\main\resources\lib\ojdbc6-12.1.0.2.jar
    -DgroupId=com.oracle
    -DartifactId=ojdbc6
    -Dversion=12.1.0.2
    -Dpackaging=jar
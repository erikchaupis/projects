Change path to alfresco to newPathForAlfresco

* service alfresco stop

1) rm -rf alfresco solr solr4
2) mv alfresco.war newPathForAlfresco.war
3) vim /apps/opt/alfresco-5.0.d/tomcat/shared/classes/alfresco-global.properties
 * alfresco.context=alfresco to newPathForAlfresco
4) vim /apps/opt/alfresco-5.0.d/solr4/archive-SpacesStore/conf/solrcore.properties
 * alfresco.baseUrl=/alfresco to newPathForAlfresco
5) vim /apps/opt/alfresco-5.0.d/solr4/workspace-SpacesStore/conf/solrcore.properties
 * alfresco.baseUrl=/alfresco to newPathForAlfresco

* service alfresco start

 ** Aditional 

AWE
 vim /apps/opt/alfresco-5.0.d/tomcat/shared/classes/alfresco/web-extension/awe-config-custom.xml

Add the following xml into alfresco-config tag: (change http://MYSERVER:MYPORT/alfresco/s  to 

http://localhost:8080/newPathForAlfresco/s)

-----------------------------------------------------

   <plug-ins> 
      <element-readers> 
         <element-reader element-name="remote" class="org.springframework.extensions.config.RemoteConfigElementReader" 

/> 
      </element-readers> 
   </plug-ins> 

   <config evaluator="string-compare" condition="Remote"> 
      <remote> 
         <endpoint> 
            <id>alfresco</id> 
            <name>Alfresco - user access</name> 
            <description>Access to Alfresco Repository WebScripts that require user authentication</description> 
            <connector-id>alfresco</connector-id> 
            <endpoint-url>http://MYSERVER:MYPORT/alfresco/s 
            </endpoint-url> 
            <identity>user</identity> 
         </endpoint> 
      </remote> 
   </config> 
-------------------------------------------

From: http://docs.alfresco.com/5.0/concepts/awe-deploy-overview.html

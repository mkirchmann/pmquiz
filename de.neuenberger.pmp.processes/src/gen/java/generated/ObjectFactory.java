//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.06 at 01:26:05 AM MESZ 
//


package generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PMPProcesses_QNAME = new QName("", "PMP_Processes");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CplxGroup }
     * 
     */
    public CplxGroup createCplxGroup() {
        return new CplxGroup();
    }

    /**
     * Create an instance of {@link CplxProcessGroup }
     * 
     */
    public CplxProcessGroup createCplxProcessGroup() {
        return new CplxProcessGroup();
    }

    /**
     * Create an instance of {@link CplxAddition }
     * 
     */
    public CplxAddition createCplxAddition() {
        return new CplxAddition();
    }

    /**
     * Create an instance of {@link CplxNamed }
     * 
     */
    public CplxNamed createCplxNamed() {
        return new CplxNamed();
    }

    /**
     * Create an instance of {@link CplxKnowledgeArea }
     * 
     */
    public CplxKnowledgeArea createCplxKnowledgeArea() {
        return new CplxKnowledgeArea();
    }

    /**
     * Create an instance of {@link CplxProcess }
     * 
     */
    public CplxProcess createCplxProcess() {
        return new CplxProcess();
    }

    /**
     * Create an instance of {@link CplxProcessGroup.Process }
     * 
     */
    public CplxProcessGroup.Process createCplxProcessGroupProcess() {
        return new CplxProcessGroup.Process();
    }

    /**
     * Create an instance of {@link CplxDefinition }
     * 
     */
    public CplxDefinition createCplxDefinition() {
        return new CplxDefinition();
    }

    /**
     * Create an instance of {@link CplxProcessGroups }
     * 
     */
    public CplxProcessGroups createCplxProcessGroups() {
        return new CplxProcessGroups();
    }

    /**
     * Create an instance of {@link CplxInputOutput }
     * 
     */
    public CplxInputOutput createCplxInputOutput() {
        return new CplxInputOutput();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CplxProcessGroups }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "PMP_Processes")
    public JAXBElement<CplxProcessGroups> createPMPProcesses(CplxProcessGroups value) {
        return new JAXBElement<CplxProcessGroups>(_PMPProcesses_QNAME, CplxProcessGroups.class, null, value);
    }

}

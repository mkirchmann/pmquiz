//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2017.12.12 um 10:14:58 AM CET 
//


package generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für cplxProcessGroups complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="cplxProcessGroups"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="KnowledgeArea" type="{}cplxKnowledgeArea" maxOccurs="unbounded"/&gt;
 *         &lt;element name="ProcessGroup" type="{}cplxProcessGroup" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cplxProcessGroups", propOrder = {
    "knowledgeArea",
    "processGroup"
})
public class CplxProcessGroups {

    @XmlElement(name = "KnowledgeArea", required = true)
    protected List<CplxKnowledgeArea> knowledgeArea;
    @XmlElement(name = "ProcessGroup", required = true)
    protected List<CplxProcessGroup> processGroup;

    /**
     * Gets the value of the knowledgeArea property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the knowledgeArea property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKnowledgeArea().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CplxKnowledgeArea }
     * 
     * 
     */
    public List<CplxKnowledgeArea> getKnowledgeArea() {
        if (knowledgeArea == null) {
            knowledgeArea = new ArrayList<CplxKnowledgeArea>();
        }
        return this.knowledgeArea;
    }

    /**
     * Gets the value of the processGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the processGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProcessGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CplxProcessGroup }
     * 
     * 
     */
    public List<CplxProcessGroup> getProcessGroup() {
        if (processGroup == null) {
            processGroup = new ArrayList<CplxProcessGroup>();
        }
        return this.processGroup;
    }

}

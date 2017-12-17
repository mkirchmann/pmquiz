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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für cplxProcess complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="cplxProcess"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}cplxNamed"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="ProcessParameters" type="{}cplxProcessParameters" minOccurs="0"/&gt;
 *         &lt;element name="SubProcess" type="{}cplxProcess" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/choice&gt;
 *       &lt;attribute name="knowledgeAreaId" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cplxProcess", propOrder = {
    "processParameters",
    "subProcess"
})
public class CplxProcess
    extends CplxNamed
{

    @XmlElement(name = "ProcessParameters")
    protected CplxProcessParameters processParameters;
    @XmlElement(name = "SubProcess")
    protected List<CplxProcess> subProcess;
    @XmlAttribute(name = "knowledgeAreaId")
    protected String knowledgeAreaId;

    /**
     * Ruft den Wert der processParameters-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CplxProcessParameters }
     *     
     */
    public CplxProcessParameters getProcessParameters() {
        return processParameters;
    }

    /**
     * Legt den Wert der processParameters-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CplxProcessParameters }
     *     
     */
    public void setProcessParameters(CplxProcessParameters value) {
        this.processParameters = value;
    }

    /**
     * Gets the value of the subProcess property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subProcess property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubProcess().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CplxProcess }
     * 
     * 
     */
    public List<CplxProcess> getSubProcess() {
        if (subProcess == null) {
            subProcess = new ArrayList<CplxProcess>();
        }
        return this.subProcess;
    }

    /**
     * Ruft den Wert der knowledgeAreaId-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKnowledgeAreaId() {
        return knowledgeAreaId;
    }

    /**
     * Legt den Wert der knowledgeAreaId-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKnowledgeAreaId(String value) {
        this.knowledgeAreaId = value;
    }

}

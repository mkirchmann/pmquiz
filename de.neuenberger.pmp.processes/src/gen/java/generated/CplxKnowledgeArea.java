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
 * <p>Java-Klasse für cplxKnowledgeArea complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="cplxKnowledgeArea"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}cplxNamed"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Process" type="{}cplxProcess" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Addition" type="{}cplxAddition" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cplxKnowledgeArea", propOrder = {
    "process",
    "addition"
})
public class CplxKnowledgeArea
    extends CplxNamed
{

    @XmlElement(name = "Process")
    protected List<CplxProcess> process;
    @XmlElement(name = "Addition")
    protected CplxAddition addition;
    @XmlAttribute(name = "id")
    protected String id;

    /**
     * Gets the value of the process property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the process property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProcess().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CplxProcess }
     * 
     * 
     */
    public List<CplxProcess> getProcess() {
        if (process == null) {
            process = new ArrayList<CplxProcess>();
        }
        return this.process;
    }

    /**
     * Ruft den Wert der addition-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CplxAddition }
     *     
     */
    public CplxAddition getAddition() {
        return addition;
    }

    /**
     * Legt den Wert der addition-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CplxAddition }
     *     
     */
    public void setAddition(CplxAddition value) {
        this.addition = value;
    }

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}

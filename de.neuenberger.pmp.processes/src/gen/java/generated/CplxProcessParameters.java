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
 * <p>Java-Klasse für cplxProcessParameters complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="cplxProcessParameters"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Input" type="{}cplxProcessResult" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Output" type="{}cplxProcessResult" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ToolOrTechnique" type="{}cplxToolOrTechnique" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cplxProcessParameters", propOrder = {
    "input",
    "output",
    "toolOrTechnique"
})
public class CplxProcessParameters {

    @XmlElement(name = "Input")
    protected List<CplxProcessResult> input;
    @XmlElement(name = "Output")
    protected List<CplxProcessResult> output;
    @XmlElement(name = "ToolOrTechnique")
    protected List<CplxToolOrTechnique> toolOrTechnique;

    /**
     * Gets the value of the input property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the input property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInput().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CplxProcessResult }
     * 
     * 
     */
    public List<CplxProcessResult> getInput() {
        if (input == null) {
            input = new ArrayList<CplxProcessResult>();
        }
        return this.input;
    }

    /**
     * Gets the value of the output property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the output property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOutput().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CplxProcessResult }
     * 
     * 
     */
    public List<CplxProcessResult> getOutput() {
        if (output == null) {
            output = new ArrayList<CplxProcessResult>();
        }
        return this.output;
    }

    /**
     * Gets the value of the toolOrTechnique property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the toolOrTechnique property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getToolOrTechnique().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CplxToolOrTechnique }
     * 
     * 
     */
    public List<CplxToolOrTechnique> getToolOrTechnique() {
        if (toolOrTechnique == null) {
            toolOrTechnique = new ArrayList<CplxToolOrTechnique>();
        }
        return this.toolOrTechnique;
    }

}

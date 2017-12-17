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
 * <p>Java-Klasse für cplxGroup complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="cplxGroup"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}cplxNamed"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Definition" type="{}cplxDefinition" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="LevelledDefinition" type="{}cplxDefinitionWithLevels" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cplxGroup", propOrder = {
    "definition",
    "levelledDefinition"
})
public class CplxGroup
    extends CplxNamed
{

    @XmlElement(name = "Definition")
    protected List<CplxDefinition> definition;
    @XmlElement(name = "LevelledDefinition")
    protected List<CplxDefinitionWithLevels> levelledDefinition;

    /**
     * Gets the value of the definition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the definition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDefinition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CplxDefinition }
     * 
     * 
     */
    public List<CplxDefinition> getDefinition() {
        if (definition == null) {
            definition = new ArrayList<CplxDefinition>();
        }
        return this.definition;
    }

    /**
     * Gets the value of the levelledDefinition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the levelledDefinition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLevelledDefinition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CplxDefinitionWithLevels }
     * 
     * 
     */
    public List<CplxDefinitionWithLevels> getLevelledDefinition() {
        if (levelledDefinition == null) {
            levelledDefinition = new ArrayList<CplxDefinitionWithLevels>();
        }
        return this.levelledDefinition;
    }

}

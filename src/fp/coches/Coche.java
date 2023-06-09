package fp.coches;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import fp.common.ColorCoche;
import fp.common.Motor;
import fp.common.TipoCoche;
import fp.utiles.Checkers;
import fp.utiles.Parsers;

public class Coche implements Comparable<Coche>{
	private String fabricante;
	private String modelo;
	private Motor motor;
	private TipoCoche tipo;
	private Integer precio;
	private LocalDate salidaMercado;
	private List<ColorCoche> listaColores = new ArrayList<ColorCoche>();
	
	public Coche(String fab,String mod,Motor motor,TipoCoche tipo,Integer prec,LocalDate salMerc,List<ColorCoche> cols) {
		/* Constructor que recibe todas las propiedades básicas para 
		 * luego generar tipos base con dichas propiedades.
		 */
		Checkers.check("El precio no puede ser negativo",prec>=0);
		Checkers.check("Los colores no pueden ser null", listaColores!=null);
		this.fabricante = fab;
		this.modelo = mod;
		this.motor = motor;
		this.tipo = tipo;
		this.precio = prec;
		this.salidaMercado = salMerc;
		this.listaColores = cols;
	}
	
	public Coche(String fab, String mod, List<ColorCoche> cols) {
		/* Constructor que recibe el nombre del fabricante (String), 
		 * el modelo (String) y una lista de colores (List<ColorCoche>)
		 * para generar tipos base pero con propiedades reducidas y 
		 * consideradas importantes.
		 */
		Checkers.check("Los colores no pueden ser null", listaColores!=null);
		this.fabricante = fab;
		this.modelo = mod;
		this.listaColores = cols;
	}
	
	//GETTERS Y SETTERS
	public String getFabricante() {
		return fabricante;
	}

	public String getModelo() {
		return modelo;
	}

	public Motor getMotor() {
		return motor;
	}

	public Integer getPrecio() {
		return this.precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	
	public LocalDate getSalidaMercado() {
		return salidaMercado;
	}

	public TipoCoche getTipo() {
		return tipo;
	}

	public List<ColorCoche> getListaColores() {
		return listaColores;
	}
	
	//FORMATO CORTO
	public String getFormatoCorto() {
		return getFabricante() + " " + 
			   getModelo() + " (" + 
			   getPrecio() + "€) - " + 
			   getTipo() + " - " + 
			   Parsers.salidaMercadoParser(getSalidaMercado()) + " - " + 
			   getListaColores();
	}
	
	//TOSTRING
	@Override
	public String toString() {
		return "Coche [fabricante=" + fabricante + ", modelo=" + modelo + ", motor=" + motor + ", tipo=" + tipo
				+ ", precio=" + precio + ", salidaMercado=" + salidaMercado + ", listaColores=" + listaColores + "]";
	}

	// HASHCODE
	@Override
	public int hashCode() {
		return Objects.hash(fabricante, listaColores, modelo, motor, precio, salidaMercado, tipo);
	}

	// EQUALS
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		return Objects.equals(fabricante, other.fabricante) && Objects.equals(listaColores, other.listaColores)
				&& Objects.equals(modelo, other.modelo) && Objects.equals(motor, other.motor)
				&& Objects.equals(precio, other.precio) && Objects.equals(salidaMercado, other.salidaMercado)
				&& tipo == other.tipo;
	}

	// COMPARETO
	@Override
	public int compareTo(Coche c) { 
		// Compara todas las propiedades en cascada
		int res;
		if(c==null) {
			throw new NullPointerException();
		}
		res = getFabricante().compareTo(c.getFabricante());
		if(res == 0) {
			res = getModelo().compareTo(c.getModelo());
			if(res==0) {
				res = getMotor().compareTo(c.getMotor());
				if(res==0) {
					res = getPrecio().compareTo(c.getPrecio());
					if(res==0) {
						res = getSalidaMercado().compareTo(c.getSalidaMercado());
					}
				}
			}
		}
		return res;
	}
}

/**
 * 
 */
package objetos;

import java.io.Serializable;
import java.util.regex.Pattern;

import estructura.enumeraciones.Iva;
import estructura.exceptions.NombreNoValidoException;
import estructura.exceptions.PrecioNoValidoException;
import estructura.exceptions.StockNoValidoException;

/**
 *	@author javipl
 *	Producto
 */
public class Producto implements Comparable<Producto>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int numProducto =1;
	private int identificador;
	private String nombre;
	private String descripcion;
	private int stock;
	private double precio;
	private Iva iva;
	private static final Pattern PATRONOMBRE = Pattern.compile("([a-zA-Z){2,}");
	private static final Pattern PATRONPRECIO =  Pattern.compile("([0-9]\\.?){1,}");
	private static final Pattern PATRONSTOCK =  Pattern.compile("[0-9]{1,}");
	/**
	 * Constructor
	 * @throws NombreNoValidoException 
	 * @throws StockNoValidoException 
	 * @throws PrecioNoValidoException 
	 * */
	public Producto(String nombre, String descripcion,int stock,double precio, Iva iva) throws NombreNoValidoException, StockNoValidoException, PrecioNoValidoException {
		setNombre(nombre);
		setDescripcion(descripcion);
		setStock(stock);
		setPrecio(precio);
		incrementarIdentificador();
		setIva(iva);
	}
	
	public Producto(int identificador){
		this.identificador =  identificador;
	}
	
	public Producto(int identificador,String nombre,String descripcion,int cantidad,double precio,Iva iva) throws NombreNoValidoException, StockNoValidoException, PrecioNoValidoException{
		this.identificador = identificador;
		setDescripcion(descripcion);
		setNombre(nombre);
		setStock(cantidad);
		setPrecio(precio);
		setIva(iva);
	}
	
	/**
	 * @return Iva del producto.
	 * */
	public Iva getIva() {
		return iva;
	}

	/**
	 * Asigna Iva al producto.
	 * @param iva, a asignar al producto 
	 * */
	public void setIva(Iva iva) {
		this.iva = iva;
	}

	
	/**
	 * @return identificador unívoco del producto  
	 * */
	protected int getIdentificador() {
		return identificador;
	}
	
	/**
	 * Incrementa el identificador.
	 * */
	private void incrementarIdentificador() {
		this.identificador = numProducto++;
	}
	
	/**
	 * @return nombre del producto
	 * */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Asigna un nombre al producto
	 * @param nombre, a asignar al producto
	 * @throws NombreNoValidoException
	 * */
	public void setNombre(String nombre) throws NombreNoValidoException {
		if(!PATRONOMBRE.matcher(nombre).matches())
			throw new NombreNoValidoException("ERROR:Nombre incorrecto");
		this.nombre = nombre;
	}
	
	/**
	 * @return descripcion del articulo
	 * */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Asginar al producto una descripcion
	 * @param descripcion, a asignar al producto
	 * */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * @return stock del producto.
	 * */
	public int getStock() {
		return stock;
	}
	/**
	 * Asignar un stock al producto
	 * @param stock, a asignar al producto.
	 * @throws StockNoValidoException
	 * */
	public void setStock(int stock) throws StockNoValidoException {
		if(!PATRONSTOCK.matcher(String.valueOf(stock)).matches()||stock<0)
			throw new StockNoValidoException("ERROR:Stock incorrecto");
		this.stock = stock;
	}
	
	/**
	 * Incrementar el stock del producto con la cantidad indicada
	 * @param stockInc, que incrementará el actual stock
	 * */
	public void incrementarStock(int stockInc) throws StockNoValidoException{
		try{
			setStock(getStock()+stockInc);
		}catch(StockNoValidoException|NumberFormatException e){
			throw new StockNoValidoException("ERROR:Stock incorrecto");
		}
		
		
	}
	
	/**
	 * Incrementar el precio del articulo
	 * @param dinero a incrementar
	 * @throws PrecioNoValidoException 
	 */
	public void incrementarPrecio(double precioIncrementar) throws PrecioNoValidoException{
		setPrecio(getPrecio()+precioIncrementar);
	}
	
	/**
	 * Decremenar stock con una cantidad indicada.
	 * @param stockDec, a decrementar
	 * */
	public void decrementarStock(int stockDec) throws StockNoValidoException{
		try{
			if((getStock()-stockDec)>=0)
				setStock(getStock()-stockDec);
			else
				throw new StockNoValidoException("ERROR: El stock a reducir no puede ser superior al actual.");
		}catch(StockNoValidoException|NumberFormatException e){
			throw new StockNoValidoException("ERROR:Stock incorrecto");
		}
		
	}
	
	/**
	 * @return precio del producto
	 * */
	public double getPrecio() {
		return precio;
	}
	
	/**
	 * Asignar un precio al producto.
	 * @param precio, a asignar.
	 * */
	public void setPrecio(double precio) throws PrecioNoValidoException {
		if(!PATRONPRECIO.matcher(String.valueOf(precio)).matches())
			throw new PrecioNoValidoException("ERROR:Precio incorrecto");
		this.precio = precio;
	}

	@Override
	public int compareTo(Producto o) {
		if(this.getStock() == o.getStock())
			return 0;
		else if (this.getStock()<o.getStock())
			return -1;
		return 1;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + identificador;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Producto other = (Producto) obj;
		if (identificador != other.identificador)
			return false;
		return true;
	}
	
	
	
	@Override
	public String toString() {
		return getClass().getSimpleName()+" ID= " + identificador + ", nombre= " + nombre + ", descripcion= " + descripcion
				+ ", stock= " + stock + ", precio= " + precio + ", iva= " + iva.valor();
	}
	
	
}

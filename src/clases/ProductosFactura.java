package clases;

public class ProductosFactura {
	
	private int cantidadProducto;
	private String nombreProducto;
	private float precioProducto;
	
	public ProductosFactura(int cantidadProducto, String nombreProducto, float precioProducto) {
		super();
		this.cantidadProducto = cantidadProducto;
		this.nombreProducto = nombreProducto;
		this.precioProducto = precioProducto;
	}

	public int getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public float getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(float precioProducto) {
		this.precioProducto = precioProducto;
	}
	
		

}

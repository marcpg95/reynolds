package estructura.enumeraciones;

/**
 * @version 1.0
 * */
public enum Iva {
	IVA10(0.10F),
	IVA21(0.21F);
	
	private float iva;
	private Iva(float iva) {
		this.iva=iva;
	}
	
	/**
	 * @return valor de iva.
	 * */
	public float valor() {
		return iva;
	}
}

public class RespuestaArchivo {

	private String respuesta;
	private byte[] archivo;
	
	public RespuestaArchivo(String respuesta, byte[] archivo){
		this.respuesta = respuesta;
		this.archivo = archivo;
	}
	
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public byte[] getArchivo() {
		return archivo;
	}
	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}
	
}

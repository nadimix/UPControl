package management;

public class ClassHotSpot {
	public String coordinates;//coordenadas
	public String time;//hora en la que se encontró
	public String date;
	public String kind;// tipo radar fijo, radar movil, punto negro, ctrol alcoholemia
	public String description;
	
	public ClassHotSpot(String coordinates, String time, String date,
			String kind, String description) {
		super();
		this.coordinates = coordinates;
		this.time = time;
		this.date = date;
		this.kind = kind;
		this.description = description;
	}
	public String getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	


}

public class State {
	private String name;
	private String capital;
	private int population;
	private long GDP;
	
	State() {
		name = "";
		capital = "";
		population = 0;
		GDP = 0;
	}
	State(String _name,String _capital, int _population, long _GDP) {
		name = _name;
		capital = _capital;
		population = _population;
		GDP = _GDP;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public long getGDP() {
		return GDP;
	}
	public void setGDP(long gDP) {
		GDP = gDP;
	}
}

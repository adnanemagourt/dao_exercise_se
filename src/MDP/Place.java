package MDP;

public class Place {
	private Long id;
	private String name;
	public Place (Long id, String name) {
		this.name = name;
		this.id = id;
	}
	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }
    public String toString() {
    	return name;
    }
}

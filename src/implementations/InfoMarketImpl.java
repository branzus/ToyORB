package implementations;

import interfaces.InfoMarket;





public class InfoMarketImpl implements InfoMarket{
	public String get_road_info(int road) {
		if(road == 1) 
			return "UNU";
		else if (road == 2)
			return "DOI";
		else return "FMM";
	}
	
	public float get_temp(String city) {
		if(city.equals("bucu")){
			return 30.2f;
		}
		else return 22.3f;
	}
}

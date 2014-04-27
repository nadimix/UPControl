package hibernate.manager;

import java.util.List;

import model.HotSpot;
import model.User;

public interface IHotSpotManager {

    public List<HotSpot> loadAllHotSpots();

    public void deleteHotSpot(HotSpot hotspot);

    public void updateHotSpot(HotSpot hotspot);

    public List<HotSpot> loadAllUserHotpots(User user);

    public Boolean saveNewHotSpot(HotSpot hot, User user);

    public HotSpot findHotSpotById(Integer id);

    public void saveHotSpotUser(User user, HotSpot hotspot);
    
    public HotSpot findByDescription(String description);

}

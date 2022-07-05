import entity.StationsEntity;
import entity.TimesEntity;
import entity.TrainsEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import javax.persistence.TypedQuery;
import javax.swing.*;

import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

class ViewTest {

    @Test
    void addStationGoodTest() {
        JFrame addFrame = new JFrame("Add station");
        View mainForm = new View();
        View.mainFrame = new JFrame("Trains&Stations manager");
        View.mainFrame.setContentPane(mainForm.mainPanel);
        View.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        View.mainFrame.pack();
        StationsEntity station = new StationsEntity();
        AddOrModifyStation adding = new AddOrModifyStation(addFrame, View.mainFrame);
        adding.setName("TEST Stacja");
        adding.setNum(2);
        TypedQuery<StationsEntity> stationByName = View.man.createNamedQuery("StationsEntity.ByName", StationsEntity.class);
        stationByName.setParameter(1, adding.getName());
        while(stationByName.getResultList().size() != 0) {
            mainForm.deleteStation("TEST Stacja");
            System.out.println("siema");
        }
        mainForm.addStation(station, adding, addFrame);
        stationByName = View.man.createNamedQuery("StationsEntity.ByName", StationsEntity.class);
        stationByName.setParameter(1, adding.getName());
        assert(stationByName.getResultList().size() == 1):"Something's wrong, I can feel it";
    }

    @Test
    void addTrainGoodTest() {
        JFrame addFrame = new JFrame("Add train");
        View mainForm = new View();
        View.mainFrame = new JFrame("Trains&Stations manager");
        View.mainFrame.setContentPane(mainForm.mainPanel);
        View.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        View.mainFrame.pack();
        TrainsEntity train = new TrainsEntity();
        AddOrModifyTrain adding = new AddOrModifyTrain(addFrame, View.mainFrame);
        adding.setName("TEST Train");
        adding.setWeight(2000);
        adding.setType("Passenger Train");
        TypedQuery<TrainsEntity> trainByName = View.man.createNamedQuery("TrainsEntity.ByName", TrainsEntity.class);
        trainByName.setParameter(1, adding.getName());
        while(trainByName.getResultList().size() != 0) {
            mainForm.deleteTrain("TEST Train");
            System.out.println("siema");
        }
        mainForm.addTrain(train, adding, addFrame);
        trainByName = View.man.createNamedQuery("TrainsEntity.ByName", TrainsEntity.class);
        trainByName.setParameter(1, adding.getName());
        assert(trainByName.getResultList().size() == 1):"Something's wrong, I can feel it";
    }

    @Test
    void addTimeGoodTest() {
        JFrame addFrame = new JFrame("Add time");
        View mainForm = new View();
        View.mainFrame = new JFrame("Trains&Stations manager");
        View.mainFrame.setContentPane(mainForm.mainPanel);
        View.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        View.mainFrame.pack();
        TimesEntity time = new TimesEntity();
        AddOrModifyTime adding = new AddOrModifyTime(addFrame, View.mainFrame);
        adding.setStationName("TEST Stacja");
        adding.setTrainName("TEST Train");
        adding.setArrivalHourTextField(10);
        adding.setArrivalMinuteTextField(23);
        adding.setDepartureHourTextField(10);
        adding.setDepartureMinuteTextField(30);
        TypedQuery<TimesEntity> timeByNames = View.man.createNamedQuery("TimesEntity.ByNames", TimesEntity.class);
        timeByNames.setParameter(1, adding.getTrainName());
        timeByNames.setParameter(2, adding.getStationName());
        while(timeByNames.getResultList().size() != 0) {
            mainForm.deleteTime("TEST Train", "TEST Stacja");
            System.out.println("siema");
        }
        mainForm.addTime(time, adding, addFrame);

        timeByNames = View.man.createNamedQuery("TimesEntity.ByNames", TimesEntity.class);
        timeByNames.setParameter(1, adding.getTrainName());
        timeByNames.setParameter(2, adding.getStationName());
        assert(timeByNames.getResultList().size() == 1):"Something's wrong, I can feel it";
    }
    @Test
    void deleteTrainGoodTest() {
        View mainForm = new View();
        View.mainFrame = new JFrame("Trains&Stations manager");
        View.mainFrame.setContentPane(mainForm.mainPanel);
        View.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        View.mainFrame.pack();

        mainForm.deleteTrain("TEST Train");
        TypedQuery<TrainsEntity> trainByName = View.man.createNamedQuery("TrainsEntity.ByName", TrainsEntity.class);
        trainByName.setParameter(1, "TEST Train");
        assert(trainByName.getResultList().size() == 0):"Something's wrong, I can feel it";
    }

    @Test
    void modifyStationGoodTest() {
        JFrame modifyFrame = new JFrame("Modify station");
        View mainForm = new View();
        View.mainFrame = new JFrame("Trains&Stations manager");
        View.mainFrame.setContentPane(mainForm.mainPanel);
        View.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        View.mainFrame.pack();
        StationsEntity station = new StationsEntity();
        AddOrModifyStation modifying = new AddOrModifyStation(modifyFrame, View.mainFrame);
        modifying.setName("TEST Stacja1");
        modifying.setNum(2);

        mainForm.modifyStation(station, modifying, modifyFrame, "TEST Stacja");
        TypedQuery<StationsEntity> stationByName = View.man.createNamedQuery("StationsEntity.ByName", StationsEntity.class);
        stationByName.setParameter(1, modifying.getName());
        assert(stationByName.getResultList().size() == 1):"Something's wrong, I can feel it";
    }

    @Test
    void modifyTrainGoodTest() {
        JFrame modifyFrame = new JFrame("Modify train");
        View mainForm = new View();
        View.mainFrame = new JFrame("Trains&Stations manager");
        View.mainFrame.setContentPane(mainForm.mainPanel);
        View.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        View.mainFrame.pack();
        TrainsEntity train = new TrainsEntity();
        AddOrModifyTrain modifying = new AddOrModifyTrain(modifyFrame, View.mainFrame);
        modifying.setName("TEST train1");
        modifying.setType("Freight Train");
        modifying.setWeight(2000);
        TypedQuery<TrainsEntity> trainByName = View.man.createNamedQuery("TrainsEntity.ByName", TrainsEntity.class);
        trainByName.setParameter(1, "TEST Train");
        assert(trainByName.getResultList().size()==1):"Ups";

        mainForm.modifyTrain(train, modifying, modifyFrame, "TEST Train");

        trainByName = View.man.createNamedQuery("TrainsEntity.ByName", TrainsEntity.class);
        trainByName.setParameter(1, modifying.getName());
        assert(trainByName.getResultList().size() == 1):"Something's wrong, I can feel it";
    }

    @Test
    void modifyTimeGoodTest() {
    }

    @Test
    void deleteStationGoodTest() {
        View mainForm = new View();
        View.mainFrame = new JFrame("Trains&Stations manager");
        View.mainFrame.setContentPane(mainForm.mainPanel);
        View.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        View.mainFrame.pack();

        TypedQuery<StationsEntity> stationByName = View.man.createNamedQuery("StationsEntity.ByName", StationsEntity.class);
        stationByName.setParameter(1, "TEST Stacja");
        assert(stationByName.getResultList().size()==1):"Ups";

        mainForm.deleteStation("TEST Stacja");
        stationByName = View.man.createNamedQuery("StationsEntity.ByName", StationsEntity.class);
        stationByName.setParameter(1, "TEST Stacja");
        assert(stationByName.getResultList().size() == 0):"Something's wrong, I can feel it";
    }



    @Test
    void deleteTimeGoodTest() {
    }
}
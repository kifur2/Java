import entity.StationsEntity;
import entity.TimesEntity;
import entity.TrainsEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import javax.swing.*;
import java.sql.Time;
import java.util.List;

public class View {
    JPanel mainPanel;
    private JButton modifyStationButton;
    private JButton modifyTrainButton;
    private JButton addStationButton;
    private JButton addTrainButton;
    private JButton deleteStationButton;
    private JButton deleteTrainButton;
    private JButton showStationButton;
    private JButton showTrainButton;
    private JLabel stationLabel;
    private JLabel trainLabel;
    private  JTextArea textArea1;
    private JTextField searchTextField;
    private JButton showTableButton;
    private JButton deleteTimeButton;
    private JButton modifyTimeButton;
    private JButton showTimeButton;
    private JButton addTimeButton;
    private JTextField searchTextField1;

    static JFrame mainFrame;
    static EntityManager man;
    private static Session sess;

    private static EntityTransaction tr;
    private static Transaction tr1;

    public View() {
        EntityManagerFactory fac = Persistence.createEntityManagerFactory("default");
        man = fac.createEntityManager();
        sess = man.unwrap(Session.class);
        tr = man.getTransaction();
        tr1 = sess.getTransaction();
        addStationButton.addActionListener(e -> {
            JFrame addFrame = new JFrame("Add Station");
            StationsEntity station = new StationsEntity();
            AddOrModifyStation adding = new AddOrModifyStation(addFrame, mainFrame);
            addFrame.setContentPane(adding.addOrModifyStationPanel);
            addFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addFrame.pack();
            addFrame.setVisible(true);
            mainFrame.setEnabled(false);
            adding.saveButton.addActionListener(f -> {
                try {
                    addStation(station, adding, addFrame);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Fill in 'Max Weight' good way!");
                    adding.numOfPlatformsTextField.grabFocus();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Fill in 'Name' good way!");
                    adding.nameTextField.grabFocus();
                }
            });
        });
        addTrainButton.addActionListener(e -> {
            JFrame addFrame = new JFrame("Add Train");
            TrainsEntity train = new TrainsEntity();
            AddOrModifyTrain adding = new AddOrModifyTrain(addFrame, mainFrame);
            addFrame.setContentPane(adding.addOrModifyTrainPanel);
            addFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addFrame.pack();
            addFrame.setVisible(true);
            mainFrame.setEnabled(false);
            adding.saveButton.addActionListener(f -> {
                try {
                    addTrain(train, adding, addFrame);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Fill in 'Max Weight' good way!");
                    adding.weightTextField.grabFocus();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Fill in 'Name' good way!");
                    adding.nameTextField.grabFocus();
                }
            });
        });
        addTimeButton.addActionListener(e -> {
            JFrame addFrame = new JFrame("Add Time");
            TimesEntity time = new TimesEntity();
            AddOrModifyTime adding = new AddOrModifyTime(addFrame, mainFrame);
            addFrame.setContentPane(adding.addOrModifyTimePanel);
            addFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addFrame.pack();
            addFrame.setVisible(true);
            mainFrame.setEnabled(false);
            adding.saveButton.addActionListener(f -> {
                try {
                    addTime(time, adding, addFrame);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Fill the forms in a good way!");
                }
            });
        });
        modifyStationButton.addActionListener(e -> {
            try {
                if (!searchTextField.getText().equals("")) {
                    searchTextField.setEnabled(false);
                    String tmpName = searchTextField.getText();
                    searchTextField.setEnabled(true);
                    TypedQuery<StationsEntity> stationByName = man.createNamedQuery("StationsEntity.ByName", StationsEntity.class);
                    stationByName.setParameter(1, tmpName);
                    List<StationsEntity> lista = stationByName.getResultList();

                    if (lista.size() == 0) {
                        throw new IllegalArgumentException();
                    }

                    StationsEntity station = lista.get(0);
                    JFrame modifyFrame = new JFrame("Modify Station: " + tmpName);
                    AddOrModifyStation modifying = new AddOrModifyStation(modifyFrame, mainFrame);
                    modifyFrame.setContentPane(modifying.addOrModifyStationPanel);
                    modifyFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    modifyFrame.pack();
                    modifyFrame.setVisible(true);
                    mainFrame.setEnabled(false);
                    modifying.saveButton.addActionListener(f -> {
                        try {
                            modifyStation(station, modifying, modifyFrame, tmpName);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Fill in 'Max Weight' good way!");
                            modifying.numOfPlatformsTextField.grabFocus();
                        } catch (IllegalArgumentException ex) {
                            JOptionPane.showMessageDialog(null, "Fill in 'Name' good way!");
                            modifying.nameTextField.grabFocus();
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "Fill in 'Station Field:' first!");
                    searchTextField.setEnabled(true);
                    searchTextField.grabFocus();
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "There is no such station!");
                searchTextField.setEnabled(true);
                searchTextField.grabFocus();
            }
        });
        modifyTrainButton.addActionListener(e -> {
            try {
                if (!searchTextField1.getText().equals("")) {
                    searchTextField1.setEnabled(false);
                    String tmpName = searchTextField1.getText();
                    searchTextField.setEnabled(true);
                    TypedQuery<TrainsEntity> trainByName = man.createNamedQuery("TrainsEntity.ByName", TrainsEntity.class);
                    trainByName.setParameter(1, tmpName);
                    List<TrainsEntity> lista = trainByName.getResultList();

                    if (lista.size() == 0) {
                        throw new IllegalArgumentException();
                    }

                    TrainsEntity train = lista.get(0);
                    JFrame modifyFrame = new JFrame("Modify Train: " + tmpName);
                    searchTextField1.setEnabled(true);
                    AddOrModifyTrain modifying = new AddOrModifyTrain(modifyFrame, mainFrame);
                    modifyFrame.setContentPane(modifying.addOrModifyTrainPanel);
                    modifyFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    modifyFrame.pack();
                    modifyFrame.setVisible(true);
                    mainFrame.setEnabled(false);
                    modifying.saveButton.addActionListener(f -> {
                        try {
                            modifyTrain(train, modifying, modifyFrame, tmpName);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Fill in 'Max Weight' good way!");
                            modifying.weightTextField.grabFocus();
                        } catch (IllegalArgumentException ex) {
                            JOptionPane.showMessageDialog(null, "Fill in 'Name' good way!");
                            modifying.nameTextField.grabFocus();
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "Fill in 'Train Field:' first!");
                    searchTextField1.setEnabled(true);
                    searchTextField1.grabFocus();
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "There is no such train!");
                searchTextField1.setEnabled(true);
                searchTextField1.grabFocus();
            }
        });

        modifyTimeButton.addActionListener(e -> {
            try {
                if (!searchTextField1.getText().equals("")) {
                    if (!searchTextField.getText().equals("")) {
                        searchTextField.setEnabled(false);
                        searchTextField1.setEnabled(false);
                        TypedQuery<TimesEntity> timeByNames = man.createNamedQuery("TimesEntity.ByNames", TimesEntity.class);
                        timeByNames.setParameter(1, searchTextField1.getText());
                        timeByNames.setParameter(2, searchTextField.getText());
                        List<TimesEntity> lista = timeByNames.getResultList();

                        if (lista.size() == 0) {
                            throw new IllegalArgumentException();
                        }
                        TimesEntity time = lista.get(0);
                        JFrame modifyFrame = new JFrame("Modify Time for: " + searchTextField.getText() + " and " + searchTextField1.getText());
                        searchTextField.setEnabled(true);
                        searchTextField1.setEnabled(true);
                        AddOrModifyTime modifying = new AddOrModifyTime(modifyFrame, mainFrame);
                        modifyFrame.setContentPane(modifying.addOrModifyTimePanel);
                        modifyFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        modifyFrame.pack();
                        modifyFrame.setVisible(true);
                        mainFrame.setEnabled(false);
                        modifying.saveButton.addActionListener(f -> {
                            try {
                                modifyTime(time, modifying, modifyFrame);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Fill the forms in a good way!");
                            }
                        });
                    } else {
                        JOptionPane.showMessageDialog(null, "Fill in 'Station Field:' first!");
                        searchTextField.setEnabled(true);
                        searchTextField.grabFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Fill in 'Train Field:' first!");
                    searchTextField1.setEnabled(true);
                    searchTextField1.grabFocus();
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "There is no such time!");
                searchTextField1.setEnabled(true);
                searchTextField.setEnabled(true);
                searchTextField.grabFocus();
            }
        });

        showTrainButton.addActionListener(e -> {
            try {
                showTrain();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "There are no trains");
            }
        });

        showStationButton.addActionListener(e -> {
            try {
                showStation();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "There are no stations");
            }
        });
        showTimeButton.addActionListener(e -> {
            try {
                showTime();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "There are no times");
            }
        });

        showTableButton.addActionListener(e -> {
            try {
                showTable();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "There are no times for this station");
            }
        });

        deleteStationButton.addActionListener(e -> {
            try {
                if (!searchTextField.getText().equals("")) {
                    deleteStation(searchTextField.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "Fill in 'Station Field:' first!");
                    searchTextField.setEnabled(true);
                    searchTextField.grabFocus();
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "There is no such station!");
                searchTextField.setEnabled(true);
                searchTextField.grabFocus();
            }
        });

        deleteTrainButton.addActionListener(e -> {
            try {
                if (!searchTextField1.getText().equals("")) {
                    deleteTrain(searchTextField1.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "Fill in 'Train Field:' first!");
                    searchTextField1.setEnabled(true);
                    searchTextField1.grabFocus();
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "There is no such Train!");
                searchTextField1.setEnabled(true);
                searchTextField1.grabFocus();
            }
        });
        deleteTimeButton.addActionListener(e -> {
            try {
                if (!searchTextField1.getText().equals("")) {
                    if (!searchTextField.getText().equals("")) {
                        deleteTime(searchTextField1.getText(), searchTextField.getText());
                    } else {
                        JOptionPane.showMessageDialog(null, "Fill in 'Station Field:' first!");
                        searchTextField.setEnabled(true);
                        searchTextField.grabFocus();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Fill in 'Train Field:' first!");
                    searchTextField1.setEnabled(true);
                    searchTextField1.grabFocus();
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "There is no such Time!");
                searchTextField1.setEnabled(true);
                searchTextField.setEnabled(true);
                searchTextField.grabFocus();
            }
        });
    }

    public static void main(String[] args) {

        mainFrame = new JFrame("Trains&Stations manager");
        View mainForm = new View();
        mainFrame.setContentPane(mainForm.mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setSize(800, 300);
        mainFrame.setVisible(true);

    }

    public void addStation(StationsEntity station, AddOrModifyStation adding, JFrame addFrame) {
        try {
            tr.begin();
            TypedQuery<StationsEntity> stationByName = man.createNamedQuery("StationsEntity.ByName", StationsEntity.class);
            stationByName.setParameter(1, adding.getName());
            if (adding.getName().equals("") || stationByName.getResultList().size() != 0)
                throw new IllegalArgumentException();
            station.setName(adding.getName());
            station.setPlatformsNumber(adding.getNum());
            man.persist(station);
            tr.commit();
            writeSth("Station added!");
        } finally {
            if (tr.isActive())
                tr.rollback();
        }
        addFrame.dispose();
        mainFrame.setEnabled(true);
        mainFrame.requestFocus();
    }

    public void addTrain(TrainsEntity train, AddOrModifyTrain adding, JFrame addFrame) {
        try {
            tr.begin();
            TypedQuery<TrainsEntity> trainByName = man.createNamedQuery("TrainsEntity.ByName", TrainsEntity.class);
            trainByName.setParameter(1, adding.getName());
            if (adding.getName().equals("") || trainByName.getResultList().size() != 0)
                throw new IllegalArgumentException();
            train.setName(adding.getName());
            train.setType(adding.getType());
            train.setMaxWeight(adding.getWeight());
            man.persist(train);
            tr.commit();
            writeSth("Train added!");
        } finally {
            if (tr.isActive())
                tr.rollback();
        }
        addFrame.dispose();
        mainFrame.setEnabled(true);
        mainFrame.requestFocus();
    }

    public void addTime(TimesEntity time, AddOrModifyTime adding, JFrame addFrame) {
        try {
            tr.begin();
            time.setDepartureTime(adding.getDeparture());
            time.setArrivalTime(adding.getArrival());
            TypedQuery<TrainsEntity> trainByName = man.createNamedQuery("TrainsEntity.ByName", TrainsEntity.class);
            TypedQuery<StationsEntity> stationByName = man.createNamedQuery("StationsEntity.ByName", StationsEntity.class);
            trainByName.setParameter(1, adding.getTrainName());
            stationByName.setParameter(1, adding.getStationName());
            if (adding.getStationName().equals("") || trainByName.getResultList().size() != 1 || adding.getTrainName().equals("") || stationByName.getResultList().size() != 1)
                throw new IllegalArgumentException();
            time.setTrainsByTrainId(trainByName.getResultList().get(0));
            time.setStationsByStationId(stationByName.getResultList().get(0));
            man.persist(time);
            tr.commit();
            writeSth("Time added!");
        } finally {
            if (tr.isActive())
                tr.rollback();
        }
        addFrame.dispose();
        mainFrame.setEnabled(true);
        mainFrame.requestFocus();
    }

    public void modifyStation(StationsEntity station, AddOrModifyStation modifying, JFrame modifyFrame, String tmpName) {
        try {
            tr1.begin();
            TypedQuery<StationsEntity> stationByName1 = man.createNamedQuery("StationsEntity.ByName", StationsEntity.class);
            stationByName1.setParameter(1, modifying.getName());
            if (modifying.getName().equals("") || (!modifying.getName().equals(tmpName) && stationByName1.getResultList().size() != 0))
                throw new IllegalArgumentException();
            station.setName(modifying.getName());
            station.setPlatformsNumber(modifying.getNum());
            sess.update(station);
            tr1.commit();
            writeSth("Station modified!");
        } finally {
            if (tr1.isActive())
                tr1.rollback();
        }
        modifyFrame.dispose();
        mainFrame.setEnabled(true);
        mainFrame.requestFocus();
    }

    public void modifyTrain(TrainsEntity train, AddOrModifyTrain modifying, JFrame modifyFrame, String tmpName) {
        try {
            tr1.begin();
            TypedQuery<TrainsEntity> trainByName1 = man.createNamedQuery("TrainsEntity.ByName", TrainsEntity.class);
            trainByName1.setParameter(1, modifying.getName());
            if (modifying.getName().equals("") || (!modifying.getName().equals(tmpName) && trainByName1.getResultList().size() != 0))
                throw new IllegalArgumentException();
            train.setName(modifying.getName());
            train.setType(modifying.getType());
            train.setMaxWeight(modifying.getWeight());
            sess.update(train);
            tr1.commit();
            writeSth("Train modified!");
        } finally {
            if (tr1.isActive())
                tr1.rollback();
        }
        modifyFrame.dispose();
        mainFrame.setEnabled(true);
        mainFrame.requestFocus();
    }

    public void modifyTime(TimesEntity time, AddOrModifyTime modifying, JFrame modifyFrame) {

        try {
            tr.begin();
            time.setDepartureTime(modifying.getDeparture());
            time.setArrivalTime(modifying.getArrival());
            TypedQuery<TrainsEntity> trainByName = man.createNamedQuery("TrainsEntity.ByName", TrainsEntity.class);
            TypedQuery<StationsEntity> stationByName = man.createNamedQuery("StationsEntity.ByName", StationsEntity.class);
            trainByName.setParameter(1, modifying.getTrainName());
            stationByName.setParameter(1, modifying.getStationName());
            if (modifying.getStationName().equals("") || trainByName.getResultList().size() != 1 || modifying.getTrainName().equals("") || stationByName.getResultList().size() != 1)
                throw new IllegalArgumentException();
            time.setTrainsByTrainId(trainByName.getResultList().get(0));
            time.setStationsByStationId(stationByName.getResultList().get(0));
            sess.update(time);
            tr.commit();
            writeSth("Time modified!");
        } finally {
            if (tr.isActive())
                tr.rollback();
        }
        modifyFrame.dispose();
        mainFrame.setEnabled(true);
        mainFrame.requestFocus();
    }

    private void showTrain() {
        try {
            tr.begin();
            TypedQuery<TrainsEntity> trainByAll = man.createNamedQuery("TrainsEntity.All", TrainsEntity.class);
            List<TrainsEntity> lista = trainByAll.getResultList();

            if (lista.size() == 0) {
                throw new IllegalArgumentException();
            }
            textArea1.setText(null);

            for (TrainsEntity p : lista) {
                textArea1.append(p.toString());
            }
            tr.commit();
        } finally {
            if (tr.isActive())
                tr.rollback();
        }
    }

    private void showStation() {
        try {
            tr.begin();
            TypedQuery<StationsEntity> stationByAll = man.createNamedQuery("StationsEntity.All", StationsEntity.class);
            List<StationsEntity> lista = stationByAll.getResultList();

            if (lista.size() == 0) {
                throw new IllegalArgumentException();
            }
            textArea1.setText(null);

            for (StationsEntity p : lista) {
                textArea1.append(p.toString());
            }
            tr.commit();
        } finally {
            if (tr.isActive())
                tr.rollback();
        }
    }

    private void showTime() {
        try {
            tr.begin();
            TypedQuery<TimesEntity> timeByAll = man.createNamedQuery("TimesEntity.All", TimesEntity.class);
            List<TimesEntity> lista = timeByAll.getResultList();

            if (lista.size() == 0) {
                throw new IllegalArgumentException();
            }
            textArea1.setText(null);

            for (TimesEntity p : lista) {
                textArea1.append(p.toString());
            }
            tr.commit();
        } finally {
            if (tr.isActive())
                tr.rollback();
        }
    }

    private void showTable() {
        try {
            tr.begin();
            TypedQuery<TimesEntity> timeByStation = man.createNamedQuery("TimesEntity.ByStationName", TimesEntity.class);
            searchTextField.setEnabled(false);
            timeByStation.setParameter(1,searchTextField.getText());
            searchTextField.setEnabled(true);
            List<TimesEntity> lista = timeByStation.getResultList();

            if (lista.size() == 0) {
                throw new IllegalArgumentException();
            }
            textArea1.setText(null);

            for (TimesEntity p : lista) {
                textArea1.append(p.toString());
            }
            tr.commit();
        } finally {
            if (tr.isActive())
                tr.rollback();
        }
    }

    public void deleteStation(String stationName) {
        try {
            tr1.begin();
            TypedQuery<StationsEntity> stationByName = man.createNamedQuery("StationsEntity.ByName", StationsEntity.class);
            stationByName.setParameter(1, stationName);
            List<StationsEntity> lista = stationByName.getResultList();

            if (lista.size() == 0) {
                throw new IllegalArgumentException();
            }

            StationsEntity station = lista.get(0);


            man.remove(station);
            man.flush();
            man.clear();
            tr1.commit();
            writeSth("Station deleted!");
        } finally {
            if (tr1.isActive())
                tr1.rollback();
        }
    }

    public void deleteTrain(String trainName) {
        try {
            tr1.begin();
            TypedQuery<TrainsEntity> trainByName = man.createNamedQuery("TrainsEntity.ByName", TrainsEntity.class);
            trainByName.setParameter(1, trainName);
            List<TrainsEntity> lista = trainByName.getResultList();

            if (lista.size() == 0) {
                throw new IllegalArgumentException();
            }

            TrainsEntity train = lista.get(0);

            man.remove(train);
            man.flush();
            man.clear();
            tr1.commit();
            writeSth("Train deleted!");
        } finally {
            if (tr1.isActive())
                tr1.rollback();
        }
    }

    public void deleteTime(String trainName, String stationName) {
        try {
            tr1.begin();
            TypedQuery<TimesEntity> timeByNames = man.createNamedQuery("TimesEntity.ByNames", TimesEntity.class);
            timeByNames.setParameter(1, trainName);
            timeByNames.setParameter(2, stationName);
            List<TimesEntity> lista = timeByNames.getResultList();

            if (lista.size() == 0) {
                throw new IllegalArgumentException();
            }

            TimesEntity time = lista.get(0);

            man.remove(time);
            man.flush();
            man.clear();
            tr1.commit();
            writeSth("Time deleted!");
        } finally {
            if (tr1.isActive())
                tr1.rollback();
        }
    }

    void writeSth(String mess){
        textArea1.setText(null);
        textArea1.append(mess);
    }
}

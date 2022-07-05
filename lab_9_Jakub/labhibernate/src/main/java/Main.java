import entity.AddressInfoEntity;
import entity.PersonEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {


    private static EntityManagerFactory fac = Persistence.createEntityManagerFactory("default");
    private static EntityManager man = fac.createEntityManager();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        EntityTransaction tr = man.getTransaction();
        Session s = man.unwrap(org.hibernate.Session.class);
        Transaction tr1 = s.getTransaction();

        try {
            int c = 0;
            while (c != 5) {
                System.out.println("***********MENU***********");
                System.out.println("1 - Dodaj osobe do bazy");
                System.out.println("2 - Wypisz osoby");
                System.out.println("3 - Modyfikuj osobe w bazie");
                System.out.println("4 - Usun osobe z bazy(wraz z adresami)");
                System.out.println("5 - Zakoncz dodawanie");
                System.out.println("**************************");
                System.out.println();
                System.out.print("Wybierz opcje: ");
                c = sc.nextInt();
                System.out.println();
                sc.nextLine();
                switch (c) {
                    case 1:
                        try {
                            tr.begin();
                            System.out.println("Dodawanie osoby...");
                            PersonEntity major = new PersonEntity();
                            System.out.print("Podaj imie: ");
                            major.setpName(sc.nextLine());
                            System.out.print("Podaj nazwisko: ");
                            major.setpSurname(sc.nextLine());
                            System.out.print("Podaj email: ");
                            major.setpEmail(sc.nextLine());

                            int amount = 0;

                            do {
                                System.out.println("Ile adresow chcesz dodac?(max 2)");
                                amount = sc.nextInt();
                                sc.nextLine();
                            } while (amount <= -1 || amount >= 3);

                            major.setAddressInfosByPId(new ArrayList<AddressInfoEntity>());

                            for (int i = 0; i < amount; i++) {
                                System.out.printf("Podaj adres " + (i + 1) + "-go mieszkania/domu:");
                                System.out.println();
                                AddressInfoEntity address = new AddressInfoEntity();
                                System.out.print("Podaj ulice: ");
                                address.setStreet(sc.nextLine());
                                System.out.print("Podaj numer domu: ");
                                address.setHouseNumber(sc.nextLine());
                                System.out.print("Podaj miasto: ");
                                address.setTown(sc.nextLine());
                                System.out.print("Podaj wojewodztwo: ");
                                address.setState(sc.nextLine());
                                System.out.print("Podaj panstwo: ");
                                address.setCountry(sc.nextLine());
                                major.getAddressInfosByPId().add(address);
                                address.setPersonByAiPId(major);

                                System.out.println("Dodano adres...");
                                System.out.println();
                            }

                            man.persist(major);

                            System.out.println("Dodawanie osoby zakonczono");
                            System.out.println();
                            tr.commit();
                        } finally {
                            if (tr.isActive())
                                tr.rollback();
                        }
                        break;
                    case 2:
                        try {
                            tr.begin();

                            TypedQuery<PersonEntity> personBySth = wybieranieFiltra();

                            personBySth.setParameter(1, sc.nextLine());
                            int k = 0;
                            for (PersonEntity p : personBySth.getResultList()) {
                                System.out.println(p);
                                k++;
                            }
                            if(k == 0) {
                                System.out.println("Brak osob pasujacych do filtra");
                                break;
                            }
                            tr.commit();
                        } finally {
                            if (tr.isActive())
                                tr.rollback();
                        }

                        break;
                    case 3:
                        try {
                            tr1.begin();

                            TypedQuery<PersonEntity> personBySth = wybieranieFiltra();

                            personBySth.setParameter(1, sc.nextLine());

                            List<PersonEntity> lista = personBySth.getResultList();

                            int k = 0;
                            System.out.println("Wybierz osobe do modyfikacji: ");
                            for (PersonEntity p : personBySth.getResultList()) {
                                System.out.print( k + ": \n");
                                System.out.println(p);
                                k++;
                            }
                            if(k == 0) {
                                System.out.println("Brak osob pasujacych do filtra");
                                break;
                            }
                            int g;
                            do {
                                System.out.print("Podaj numer osoby: ");
                                g = sc.nextInt();
                                sc.nextLine();
                            }while(g <0 || g >=  k);
                            PersonEntity p = lista.get(g);
                            System.out.print("Podaj nowe imie: ");
                            p.setpName(sc.nextLine());
                            System.out.print("Podaj nowe nazwisko: ");
                            p.setpSurname(sc.nextLine());
                            System.out.print("Podaj nowy email: ");
                            p.setpEmail(sc.nextLine());

                            int i = 1;
                            for (AddressInfoEntity address : p.getAddressInfosByPId()) {
                                System.out.printf("Podaj nowy adres " + i + "-go mieszkania/domu:");
                                System.out.println();
                                System.out.print("Podaj ulice: ");
                                address.setStreet(sc.nextLine());
                                System.out.print("Podaj numer domu: ");
                                address.setHouseNumber(sc.nextLine());
                                System.out.print("Podaj miasto: ");
                                address.setTown(sc.nextLine());
                                System.out.print("Podaj wojewodztwo: ");
                                address.setState(sc.nextLine());
                                System.out.print("Podaj panstwo: ");
                                address.setCountry(sc.nextLine());
                                i++;
                            }

                            s.update(p);
                            System.out.println("Zmodyfikowano pomyslnie...");
                            tr1.commit();
                        } finally {
                            if (tr1.isActive())
                                tr1.rollback();
                        }
                        break;
                    case 4:
                        try {
                            tr1.begin();

                            TypedQuery<PersonEntity> personBySth = wybieranieFiltra();

                            personBySth.setParameter(1, sc.nextLine());

                            List<PersonEntity> lista = personBySth.getResultList();
                            int k = 0;
                            System.out.println("Wybierz osobe do usuniecia z bazy: ");
                            for (PersonEntity p : lista) {
                                System.out.print( k + ": \n");
                                System.out.println(p);
                                k++;
                            }
                            if(k == 0) {
                                System.out.println("Brak osob pasujacych do filtra");
                                break;
                            }

                            int g;
                            do {
                                System.out.print("Podaj numer osoby: ");
                                g = sc.nextInt();
                                sc.nextLine();
                            }while(g <0 || g >=  k);
                            PersonEntity p = lista.get(g);

                            man.remove(p);
                            man.flush();
                            man.clear();
                            System.out.println("Usunieto pomyslnie...");
                            tr1.commit();
                        } finally {
                            if (tr1.isActive())
                                tr1.rollback();
                        }
                    case 5:
                        break;
                    default:
                        System.out.println("Niewlasciwa komenda");
                }

            }
        }finally{
            man.close();
            fac.close();
        }

    }
    private static TypedQuery<PersonEntity> wybieranieFiltra(){
        System.out.println("*******WYSZUKIWANIE*******");
        System.out.println("1 - po imieniu");
        System.out.println("2 - po nazwisku");
        System.out.println("3 - po wojewodztwie");
        System.out.println("4 - po kraju");
        System.out.println("5 - po miescie");
        System.out.println("6 - po adresie mailowym");
        System.out.println("**************************");
        System.out.println();
        System.out.print("Wybierz opcje: ");
        int filtr = sc.nextInt();
        System.out.println();
        sc.nextLine();
        while(true) {
            switch (filtr) {
                case 1:
                    System.out.print("Podaj imie, ktore chcesz poszukac: ");
                    return man.createNamedQuery("PersonEntity.ByName", PersonEntity.class);
                case 2:
                    System.out.print("Podaj nazwisko, ktore chcesz poszukac: ");
                    return man.createNamedQuery("PersonEntity.BySurname", PersonEntity.class);
                case 3:
                    System.out.print("Podaj wojewodztwo, ktore chcesz poszukac: ");
                    return man.createNamedQuery("PersonEntity.ByState", PersonEntity.class);
                case 4:
                    System.out.print("Podaj panstwo, ktore chcesz poszukac: ");
                    return man.createNamedQuery("PersonEntity.ByCountry", PersonEntity.class);
                case 5:
                    System.out.print("Podaj miasto, ktore chcesz poszukac: ");
                    return man.createNamedQuery("PersonEntity.ByTown", PersonEntity.class);
                case 6:
                    System.out.print("Podaj mail, ktory chcesz poszukac: ");
                    return man.createNamedQuery("PersonEntity.ByEmail", PersonEntity.class);
                default:
                    System.out.println("Sprobuj jeszcze raz");
                    filtr =sc.nextInt();
                    sc.nextLine();
            }
        }
    }
}

package util;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DataProviders {

    @DataProvider
    public static Iterator<Object[]> dataProviderFirst() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/negativeLoginIncorrectTest.data")));

        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }




    @DataProvider
    public static Iterator<Object[]> dataProviderSecond() {
        List<Object[]> data = new ArrayList();
        data.add(new Object[]{"1a@yahoo.com", "password", "There isn't an account for this email"});
        data.add(new Object[]{"1yahoo", "psw2", "There isn't an account for this username"});
        data.add(new Object[]{"", "123", "Missing email"});
        data.add(new Object[]{"12345@test.com", "", "There isn't an account for this email"});

        return data.iterator();
    }


    @DataProvider
    public Iterator<Object[]> dataProviderThird() {
        List<Object[]> data = new ArrayList();

        for (int i = 0; i < 4; ++i) {
            data.add(new Object[]{this.generateRandomName(), this.generateRandomPassword()});
        }

        return data.iterator();
    }


    private Object generateRandomPassword() {

        return "pass" + (new Random()).nextInt();
    }

    private Object generateRandomName() {

        return "demo" + (new Random()).nextInt() + "@gmail.com";
    }

    @DataProvider
    public Iterator<Object[]> dataProviderThirdCard() {
        List<Object[]> data = new ArrayList();

        for (int i = 0; i < 3; ++i) {
            data.add(new Object[]{this.givenWhenGeneratingRandomAlphanumereticString()});
        }

        return data.iterator();
    }

    // Generator from internet (length only 8 symbols)
    public Object givenWhenGeneratingRandomAlphanumereticString() {
        Random random = new Random();
        int leftLimit = 48; // numeral'0'
        int rightLimit = 122; // numeral 'z'
        int targetStringLenght = 8;

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLenght)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        //System.out.println( generatedString);
        return generatedString;

    }

    @DataProvider
    public static Iterator<Object[]> dataProviderFirstList() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/listTitleField.data")));

        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> searchArticlesFromFile() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/searchArtical.data")));

        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> searchArticle() {
        List<Object[]> data = new ArrayList();
        data.add(new Object[]{"Selenium", "Selenium (software)"});
        data.add(new Object[]{"Selenium (software)", "Selenium (software)"});
        return data.iterator();
    }
}

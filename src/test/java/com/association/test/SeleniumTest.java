package com.association.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class SeleniumTest {
    public static void main(String[] args) {
        Properties config = new Properties();
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\Lenovo\\eclipse-workspace\\association-sportive\\src\\test\\java\\com\\association\\test\\ressources\\config.properties");
            config.load(fis);
        } catch (IOException e) {
            System.err.println("Error loading configuration file: " + e.getMessage());
            return;
        }

        // Retrieve WebDriver path from configuration
        String edgeDriverPath = config.getProperty("webdriver.edge.driver");
        if (edgeDriverPath == null || edgeDriverPath.isEmpty()) {
            System.err.println("Error: WebDriver path not specified in config.properties.");
            return;
        }

        // Configure Edge WebDriver
        System.setProperty("webdriver.edge.driver", edgeDriverPath);
        // Configurer EdgeOptions
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");

        // Initialiser le WebDriver
        WebDriver driver = new EdgeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            // Étape 1 : Accéder à la page d'accueil
            driver.get("http://localhost:8080/association-sportive/");
            System.out.println("Accès à la page d'accueil : " + driver.getTitle());
            waitFor(1000); 

            // Étape 2 : Accéder à la liste des adhérents
            WebElement voirListeButton = driver.findElement(By.linkText("Voir la Liste des Adhérents"));
            voirListeButton.click();
            waitFor(1000); 

            // Étape 3 : Ajouter un nouvel adhérent
            WebElement ajouterAdherentButton = driver.findElement(By.linkText("Ajouter un nouvel adhérent"));
            ajouterAdherentButton.click();
            waitFor(1000); 

            // Étape 4 : Remplir le formulaire d'adhérent
            String nomAdherent = "Dupont";
            String prenomAdherent = "Jean";
            driver.findElement(By.name("nom")).sendKeys(nomAdherent);
            driver.findElement(By.name("prenom")).sendKeys(prenomAdherent);
            driver.findElement(By.name("dateNaissance")).sendKeys("15/05/1985"); // Format correct
            driver.findElement(By.name("dateCotisation")).sendKeys("01/01/2025"); // Format correct
            driver.findElement(By.name("email")).sendKeys("jean.dupont@example.com");
            driver.findElement(By.name("adresse")).sendKeys("123 Rue de Paris");
            driver.findElement(By.name("codePostal")).sendKeys("75000");
            driver.findElement(By.name("ville")).sendKeys("Paris");
            driver.findElement(By.name("telephone")).sendKeys("0123456789");

            // Choisir un groupe
            WebElement groupeDropdown = driver.findElement(By.name("groupeId"));
            groupeDropdown.click();
            WebElement groupeOption = driver.findElement(By.xpath("//option[text()='grp1']")); // Remplacez 'grp1' par un groupe existant
            groupeOption.click();
            waitFor(1000); // Attente de 4 secondes

            // Soumettre le formulaire
            WebElement enregistrerButton = driver.findElement(By.cssSelector("button[type='submit']"));
            enregistrerButton.click();
            System.out.println("Adhérent ajouté avec succès.");
            waitFor(1000); 

            // Étape 5 : Chercher l'adhérent "Jean Dupont" et accéder à ses détails
            List<WebElement> lignesAdherents = driver.findElements(By.xpath("//table/tbody/tr"));
            for (WebElement ligne : lignesAdherents) {
                String nom = ligne.findElement(By.xpath(".//td[1]")).getText();
                String prenom = ligne.findElement(By.xpath(".//td[2]")).getText();

                if (nom.equals(nomAdherent) && prenom.equals(prenomAdherent)) {
                    WebElement detailsLink = ligne.findElement(By.linkText("Détails"));
                    detailsLink.click();
                    System.out.println("Accès aux détails de l'adhérent Jean Dupont.");
                    waitFor(2000); 
                    break;
                }
            }

            // Étape 6 : Retourner à la liste des adhérents
            WebElement retourListeButton = driver.findElement(By.linkText("Retour à la liste des adhérents"));
            retourListeButton.click();
            waitFor(1000); // Attente de 4 secondes

            // Étape 7 : Chercher l'adhérent ajouté et le supprimer
            lignesAdherents = driver.findElements(By.xpath("//table/tbody/tr"));
            for (WebElement ligne : lignesAdherents) {
                String nom = ligne.findElement(By.xpath(".//td[1]")).getText();
                String prenom = ligne.findElement(By.xpath(".//td[2]")).getText();

                if (nom.equals(nomAdherent) && prenom.equals(prenomAdherent)) {
                    WebElement supprimerButton = ligne.findElement(By.linkText("Supprimer"));
                    supprimerButton.click();
                    waitFor(1000); 
                    driver.switchTo().alert().accept();
                    System.out.println("Adhérent supprimé avec succès.");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }   
            driver.quit();
        }
    }

    private static void waitFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

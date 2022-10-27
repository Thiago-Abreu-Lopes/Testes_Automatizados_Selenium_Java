import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import java.util.concurrent.TimeUnit;
public class TestlinkTest {

    WebDriver driver;
    @Test
    public void TestLinkFull(){
        /*setup*/
        System.setProperty("webdriver.edge.driver","src\\driver\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://testlink.org/");

        /*Entrar no Git do TestLink*/
        WebElement gitLink = driver.findElement(By.xpath("/html/body/div/div[3]/div/a[3]"));
        gitLink.click();

        /*asserts para validar o git em questao*/
        // comparar About do repositório
        String aboutEsperado = "TestLink Open Source Test & Requirement Management System";
        WebElement aboutGit = driver.findElement(By.xpath("//*[@id=\"repo-content-pjax-container\"]/div/div/div[3]/div[2]/div/div[1]/div/p"));
        String aboutAtual = aboutGit.getText();
        Assert.assertEquals(aboutEsperado,aboutAtual);

        //comparando titulo do Readme
        String readmeTitleEsperado = "TestLink 1.9.20 Raijin - Read me";
        WebElement readmeTitle = driver.findElement(By.xpath("//*[@id=\"readme\"]/div[2]/article/h1[2]"));
        String readmeTitleAtual = readmeTitle.getText();
        Assert.assertEquals(readmeTitleEsperado,readmeTitleAtual);


        /*######PARTE OPICIONAL############*/
        /*Pesquisa outro Repositório*/
        WebElement gitSearch = driver.findElement(By.xpath("/html/body/div[1]/div[1]/header/div/div[2]/div/div/div[1]/div/div/form/label/input[1]"));
        gitSearch.click();
        gitSearch.sendKeys("Thiago-Abreu-Lopes/TesteUnit-rio_Java_Qualidade_de-Software_atv1");
        gitSearch.sendKeys(Keys.DOWN,Keys.DOWN,Keys.DOWN);
        gitSearch.sendKeys(Keys.ENTER);

        WebElement comeIn = driver.findElement(By.xpath("/html/body/div[1]/div[4]/main/div/div[3]/div/ul/li/div[2]/div[1]/div/a"));
        comeIn.click();

        /*assert para validar o repositorio pesquisado*/
        //comparando usuario do repositorio
        String userFromSearchEsperado = "Thiago-Abreu-Lopes";
        WebElement userFromSearch = driver.findElement(By.xpath("//*[@id=\"repository-container-header\"]/div[1]/div/div/span[1]"));
        String userFromSearchAtual = userFromSearch.getText();
        Assert.assertEquals(userFromSearchEsperado,userFromSearchAtual);

        /*Fecha o Navegador*/
        driver.quit();
    }
}

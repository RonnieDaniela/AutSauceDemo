package SauceDemo.SauceDemo;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Utilidades.CapturaEvidencia;
import Utilidades.DatosExcel;

public class Asignacion7 {
	String url = "https://www.saucedemo.com/";
	String driverPath = "..\\SauceDemo\\Drivers\\chromedriver.exe";
	WebDriver driver;
	File pantalla;
	String dirEvidencias = "..\\SauceDemo\\Evidencias\\";
	String nombreDocumento = "Evidencias-SauceDemo.docx";
	
	@BeforeSuite
	public void abrirPagina() throws InvalidFormatException, IOException, InterruptedException { 
		
		System.setProperty("webdriver.chrome.driver", driverPath);
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();	
		driver.get(url);
				
		CapturaEvidencia.escribirTituloEnDocumento(dirEvidencias + nombreDocumento, "Documento de Evidencias de Pruebas con exel Sauce ", 18);
	}
	
	@Test (dataProvider="Datos Excel")
	public void generarOrden(String username, String password, String nombre, String apellido, String codigoPostal) throws InvalidFormatException, IOException, InterruptedException {
		WebElement txtUsername = driver.findElement(By.cssSelector("#user-name"));
		txtUsername.clear();
		txtUsername.sendKeys(username);
		WebElement txtPassword = driver.findElement(By.cssSelector("#password"));
		txtPassword.clear();
		txtPassword.sendKeys(password);
		
		driver.findElement(By.cssSelector("#login-button")).click();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento, "Paso 1 - Login");
		
		String urlEsperada = "https://www.saucedemo.com/inventory.html";
		String urlActual = driver.getCurrentUrl();
		Assert.assertEquals(urlActual, urlEsperada);
		
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento, "Paso 2 - Seleccionamos el producto");
		
		driver.findElement(By.id("shopping_cart_container")).click();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento, "Paso 3 - Ingresamos al carrito");
		
		driver.findElement(By.id("checkout")).click();
		
		driver.findElement(By.cssSelector("#first-name")).sendKeys(nombre);
		driver.findElement(By.cssSelector("#last-name")).sendKeys(apellido);
		driver.findElement(By.cssSelector("#postal-code")).sendKeys(codigoPostal);
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento, "Paso 4 - Cargamos los datos de envio");
				
		driver.findElement(By.id("continue")).click();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento, "Paso 5 - Verificamos la pantalla de resumen de compra");
		
		driver.findElement(By.id("finish")).click();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento, "Paso 6 - Completamos la Compra");
		
		driver.findElement(By.id("back-to-products")).click();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento, "Paso 7 - Regresamos al menu principal");
		
		driver.findElement(By.id("react-burger-menu-btn")).click();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento, "Paso 8 - Ingresamos al Menu desplegable y deslogeamos la cuenta");
		
		driver.findElement(By.id("logout_sidebar_link")).click();
		
	}
	@AfterSuite
	public void cerrarPagina() {
		driver.close();
	}
	
	@DataProvider(name="Datos Excel")
	public Object[][] obtenerDatosExcel() throws Exception {
		return DatosExcel.leerExcel("..\\SauceDemo\\Datos\\DatosExel.xlsx", "Hoja1");
	}
}
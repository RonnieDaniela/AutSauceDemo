package SauceDemo.SauceDemo;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import Utilidades.CapturaEvidencia;

public class Asignacion5 {
	String url = "https://www.saucedemo.com/";
	String driverPath = "..\\SauceDemo\\Drivers\\chromedriver.exe";
	WebDriver driver;
	File pantalla;
	String dirEvidencias = "..\\SauceDemo\\Evidencias\\";
	String nombreDocumento = "Evidencias-SauceDemo.docx";
	
	@BeforeSuite
	public void abrirPagina() throws InvalidFormatException, IOException, InterruptedException {   // setUp
		// Todas las instrucciones que son comunes a ambos @Test al inicio 
		System.setProperty("webdriver.chrome.driver", driverPath);
		
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();	
		
		CapturaEvidencia.escribirTituloEnDocumento(dirEvidencias + nombreDocumento, "Documento de Evidencias de Prueba Sauce", 18);
		
	}
	
	@Test (description="Login", priority=1)
	public void Login() throws InvalidFormatException, IOException, InterruptedException {
		driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
		driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento, "Paso 1 - Login");
		
		driver.findElement(By.cssSelector("#login-button")).click();
		
	}
	
	@Test (description="Pre-orden", priority=5)
	public void Preorden () throws InvalidFormatException, IOException, InterruptedException {
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento, "Paso 2 - Selecciono el producto");
		
		driver.findElement(By.id("shopping_cart_container")).click();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento, "Paso 3 - Ingresamos al carrito");
		
		driver.findElement(By.cssSelector("#checkout")).click();
		
		driver.findElement(By.cssSelector("#first-name")).sendKeys("Daniela");
		driver.findElement(By.cssSelector("#last-name")).sendKeys("Fernanda");
		driver.findElement(By.cssSelector("#postal-code")).sendKeys("123456");
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento, "Paso 4 - Cargamos los datos de envio");
		
		driver.findElement(By.cssSelector("#continue")).click();
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento, "Paso 5 - Verificamos la pantalla de resumen de compra");
		
		driver.findElement(By.cssSelector("#finish")).click();
		
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "img.jpg", dirEvidencias + nombreDocumento, "Paso 6 - Completamos la Compra");
		
		String urlEsperada = "https://www.saucedemo.com/checkout-complete.html";
		String tituloEsperado = "Swag Labs";
		
		String urlActual = driver.getCurrentUrl();
		String tituloActual = driver.getTitle();
		
		Assert.assertEquals(urlActual, urlEsperada);
		Assert.assertEquals(tituloActual, tituloEsperado);
	}
	
	@AfterSuite
	public void cerrarPagina() {   // tearDown
		//driver.close();
	}

}

package Paginas;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilidades.CapturaEvidencia;

public class HomePage {
	String dirEvidencias = "..\\SauceDemo\\Evidencias\\";
	String nombreDocumento = "Evidencias-SauceDemo.docx";
		
	//Elementos
		@FindBy (css ="#user-name")
		WebElement txtUsername;
		
		@FindBy (css="#password")
		WebElement txtPassword;
		
		@FindBy (css ="#login-button")
		WebElement btnLogin;
		
		@FindBy (id ="add-to-cart-sauce-labs-backpack")
		WebElement btnAgregar;
		
		@FindBy (id="shopping_cart_container")
		WebElement bntCarrito;
		
		@FindBy (id ="checkout")
		WebElement btnCheck;
		
		@FindBy (css ="#first-name")
		WebElement txtNombre;
		
		@FindBy (css ="#last-name")
		WebElement txtApellido;
		
		@FindBy (css ="#postal-code")
		WebElement txtCodigoPostal;
		
		@FindBy (id="continue")
		WebElement btnResumen;
		
		@FindBy (id ="finish")
		WebElement btnCompra;
		
		@FindBy (id="back-to-products")
		WebElement btnInicio;
		
		@FindBy (id="react-burger-menu-btn")
		WebElement btnMenu;
		
		@FindBy (id="logout_sidebar_link")
		WebElement btnLogout;		
		
		//Contructor
		public HomePage(WebDriver driver) throws InvalidFormatException, IOException, InterruptedException {
			CapturaEvidencia.escribirTituloEnDocumento(dirEvidencias + nombreDocumento, "Documento de Evidencias de SauceDemo con la aplicacion del diagrama POM y DataProvider", 18);
			PageFactory.initElements(driver, this);
		}
		
		//Acciones sobre los elementos de las pagina web
		public void Usuario(String username) {
			txtUsername.clear();
			txtUsername.sendKeys(username);
		}
		
		public void Contraseña(String password) {
			txtPassword.clear();
			txtPassword.sendKeys(password);
		}
		public void Login () {
			btnLogin.click();
		}
		public void AgregoAlCarrito () {
			btnAgregar.click();
		}
		public void Carrito () {
			bntCarrito.click();
		}
		public void CheckOut () {
			btnCheck.click();
		}
		public void Nombre(String nombre) {
			txtNombre.sendKeys(nombre);
		}
		public void Apellido(String apellido) {
			txtApellido.sendKeys(apellido);
		}
		public void CodigoPostal(String codigoPostal) {
			txtCodigoPostal.sendKeys(codigoPostal);
		}
		public void Resumen () {
			btnResumen.click();
		}
		public void Compra () {
			btnCompra.click();
		}
		public void Inicio () {
			btnInicio.click();
		}
		public void Menu () {
			btnMenu.click();
		}
		public void Logout () {
			btnLogout.click();
		}
}

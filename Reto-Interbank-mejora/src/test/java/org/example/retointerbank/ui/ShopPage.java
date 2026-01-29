package org.example.retointerbank.ui;

import net.serenitybdd.screenplay.targets.Target;

public class ShopPage {
    public static final Target CHAQUETA_HOMBRE = Target.the("Categoría chaquetas hombre")
        .locatedBy("//a[@href='/list/mens_outerwear']");
    public static final Target CHAQUETA_MUJER = Target.the("Categoría chaquetas mujer")
        .locatedBy("//a[@href='/list/ladies_outerwear']");
    public static final Target TALLA = Target.the("Talla de chaqueta")
        .locatedBy("//select[@name='size']");
    public static final Target AGREGAR_CARRITO = Target.the("Botón agregar al carrito")
        .locatedBy("//button[contains(text(),'Add to Cart')]");
    public static final Target PRECIO_TOTAL = Target.the("Precio total")
        .locatedBy("//div[@id='total']");
    public static final Target CHECKOUT = Target.the("Botón Checkout")
        .locatedBy("//button[contains(text(),'Checkout')]");

    public static final Target MENSAJE_CONFIRMACION = Target.the("Mensaje de confirmación")
        .locatedBy("//h1[contains(text(),'Thank you')] | //div[contains(@class,'confirmation')] | //p[contains(text(),'order')]");

    public static final String SHADOW_HOST_APP = "shop-app";

    public static final String CATEGORIA_HOMBRE_SHADOW = "a[href='/list/mens_outerwear']";
    public static final String CATEGORIA_MUJER_SHADOW = "a[href='/list/ladies_outerwear']";

    public static final String SHADOW_HOST_LIST = "shop-list";

    public static final String SHADOW_FIRST_LIST_ITEM = "ul.grid li:first-of-type a";

    public static final String SHADOW_HOST_LIST_ITEM = "shop-list-item";
    public static final String SHADOW_HOST_IMAGE = "shop-image";
    public static final String SHADOW_IMG_SELECTOR = "img#img";

    public static final String SHADOW_HOST_DETAIL = "shop-detail"; // dentro de shop-app cuando page=detail
    public static final String SHADOW_HOST_SIZE_WRAPPER = "shop-select"; // envoltorio del select
    public static final String SHADOW_SIZE_SELECT = "select#sizeSelect"; // selector final de talla
    public static final String ADD_TO_CART_BUTTON = "button[aria-label='Add this item to cart']"; // botón agregar al carrito

    public static final String SHADOW_CART_MODAL = "shop-cart-modal";
    public static final String CART_MODAL_CLOSE_BTN = "#closeBtn"; // botón cerrar dentro del modal
    public static final String CART_MODAL_CHECKOUT_BTN = "a[href='/checkout']"; // botón checkout dentro del modal

    public static final String CART_ICON = "a[href='/cart']"; // link al carrito dentro de shop-app

    public static final String SHADOW_HOST_CART = "shop-cart";
    public static final String CART_TOTAL_PRICE = "span.subtotal"; // selector del precio total en el carrito

    public static final String SHADOW_HOST_CHECKOUT = "shop-checkout";

    public static final String CHECKOUT_EMAIL = "input#accountEmail";
    public static final String CHECKOUT_PHONE = "input#accountPhone";
    public static final String CHECKOUT_ADDRESS = "input#shipAddress";
    public static final String CHECKOUT_CITY = "input#shipCity";
    public static final String CHECKOUT_STATE = "input#shipState";
    public static final String CHECKOUT_ZIP = "input#shipZip";
    public static final String CHECKOUT_CARDHOLDER_NAME = "input#ccName";
    public static final String CHECKOUT_CARD_NUMBER = "input#ccNumber";
    public static final String CHECKOUT_CVV = "input#ccCVV";
    public static final String CHECKOUT_PLACE_ORDER_BTN = "input[value='Place Order']";

    public static final String CHECKOUT_SUCCESS_MESSAGE = "h1"; // "Thank you" dentro de shop-checkout
    public static final String CHECKOUT_SUCCESS_HEADER = "header[state='success']";
    public static final String CHECKOUT_SUCCESS_TITLE = "header[state='success'] h1";
    public static final String CHECKOUT_SUCCESS_PARAGRAPH = "header[state='success'] p";
}
function show_checkout_form() {
    document.getElementById('checkout').style.display = "block";
}

function show_change_order_form() {
    document.getElementById('change_order').style.display = "block";
}

function show_supplier_add_form() {
    document.getElementById('supplier_add_form').style.display = "block";
    document.getElementById('supplier_change_form').style.display = "none";
}

function show_supplier_change_form() {
    document.getElementById('supplier_change_form').style.display = "block";
    document.getElementById('supplier_add_form').style.display = "none";
}

function show_type_add_form() {
    document.getElementById('type_add_form').style.display = "block";
    document.getElementById('type_change_form').style.display = "none";
}

function show_type_change_form() {
    document.getElementById('type_change_form').style.display = "block";
    document.getElementById('type_add_form').style.display = "none";
}

function show_product_add_form() {
    document.getElementById('product_add_form').style.display = "block";
    document.getElementById('product_set_discount_form').style.display = "none";
}

function show_product_set_discount_form() {
    document.getElementById('product_set_discount_form').style.display = "block";
    document.getElementById('product_add_form').style.display = "none";
}

function show_statistics_add_form() {
    document.getElementById('statistics_add_form').style.display = "block";
}

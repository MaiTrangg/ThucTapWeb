let MY_BANK = {
    BANK_ID: 'VietinBank',
    BANK_NO: '104873782654'
};

function parseCurrency(currency) {
    // L?y t?t c? c�c s? t? chu?i, gi? l?i d?u ch?m ??u ti�n v� b? t?t c? c�c s? sau d?u ch?m
    let cleaned = currency.replace(/[^\d.]/g, ''); // Gi? l?i s? v� d?u ch?m
    let dotIndex = cleaned.indexOf('.'); // T�m v? tr� c?a d?u ch?m ??u ti�n

    if (dotIndex !== -1) {
        cleaned = cleaned.substring(0, dotIndex); // L?y ph?n chu?i tr??c d?u ch?m
    }

    return cleaned;
}


function getFormattedNumberString(number) {
    return new Intl.NumberFormat('vi-VN').format(number).replace(/,/g, '.');
}

let lastCheckedTransactionId = null; // Bi?n to�n c?c ?? l?u tr? ID giao d?ch cu?i c�ng ?� ki?m tra
let notificationSent = false; // Bi?n to�n c?c ?? ??m b?o ch? g?i th�ng b�o m?t l?n
let initialDataLength = 0; // Variable to store the initial length of data.data

document.addEventListener('DOMContentLoaded', function () {

    document.getElementById('placeOrderButton').addEventListener('click', function(event) {
        const selectedPaymentMethod = document.querySelector('input[name="paymentMethod"]:checked').value;
        event.preventDefault(); // Ng?n ch?n h�nh vi m?c ??nh c?a n�t "Place Order"

        if (selectedPaymentMethod === 'QR') {
            generateQRCode();
        } else if (selectedPaymentMethod === 'COD') {
            // G?i form ??n checkoutServlet
            document.getElementById('checkout-form').submit();
        }
    });
        // if ($(this).val() === '3') {
    // document.getElementById('qr').addEventListener("click", function (e){

    function generateQRCode() {
        var modal = document.getElementById('qrModal');
        var img = document.getElementById('QRCODE-Img');
        var paidPrice = parseCurrency(document.getElementById('totalAmount').textContent); // L?y gi� tr? t? #totalCost
        // var paidPrice = parseCurrency(totalAmount);
        var paidContent = "Chuyen Khoan"; // L?y gi� tr? t? #nameConsignee
        // if(!checkPhone() || !checkNote() || !checkUsername()){
        //
        // }

        // Hi?n th? modal
        modal.style.display = 'flex';

        // ??t ???ng d?n QR code
        var qrUrl = `https://img.vietqr.io/image/vietinbank-104873782654-compact2.jpg?amount=${paidPrice}&addInfo=${paidContent}&accountName=${paidContent}`;
        img.src = qrUrl;


        let checkInterval; // Bi?n to�n c?c ?? l?u ID c?a setInterval

        setTimeout(() => {
            checkInterval = setInterval(async () => {
                const isPaid = await checkPay(paidPrice, paidContent);
                if (isPaid) {
                    clearInterval(checkInterval); // D?ng ki?m tra khi thanh to�n th�nh c�ng
                }
            }, 1000);
        }, 20000);

        // ?n scrollbar tr�n body
        document.body.style.overflow = 'hidden';
    }

    // X? l� ?�ng modal khi click ra ngo�i modal
    window.onclick = function (event) {
        var modal = document.getElementById('qrModal');
        if (event.target == modal) {
            modal.style.display = 'none';
            document.body.style.overflow = ''; // Kh�i ph?c l?i scrollbar
        }
    };
});

let isSuccess = false;

async function checkPay(total, content) {
    if (isSuccess) {
        return true; // N?u th�ng b�o ?� ???c g?i, kh�ng c?n ki?m tra l?i
    }

    try {
        const response = await fetch('https://script.google.com/macros/s/AKfycbwrxIK2QO8awi06Fmaq8EpB2sG6S619zVHvk48oGAf3WjvU2QdfxFSK-e8EtM5Bcw/exec');
        const data = await response.json();
        console.log('Received data:', data);

        if (initialDataLength === 0) {
            initialDataLength = data.data.length; // L?u chi?u d�i ban ??u
            console.log(`Gi� tr? data.data.length l�c ban ??u: ${initialDataLength}`);
        }

        // const lastPay = data.data[data.data.length - 1];
        // const lastTotal = lastPay['Gi� tr?'];
        // const lastContent = lastPay['M� t?'];
        // console.log(lastTotal, lastContent);
        // L?y giao d?ch m?i nh?t
        const latestTransaction = data.data[data.data.length - 1];
        const { "M� GD": transactionId, "M� t?": description, "Gi� tr?": amount, "Ng�y di?n ra": date, "S? t�i kho?n": accountNumber } = latestTransaction;


        // Ki?m tra n?u s? l??ng giao d?ch ?� t?ng l�n
        if (data.data.length > initialDataLength) {
            console.log(`Gi� tr? data.data.length khi ?� chuy?n kho?n th�nh c�ng: ${data.data.length}`);
            if (!isSuccess) {
                // alert('Thanh to�n th�nh c�ng');
                // document.getElementById('payment').value = '3'; // ??t ph??ng th?c thanh to�n th�nh 3
                // document.getElementById('checkout-form').submit(); // G?i form
                // isSuccess = true; // ?�nh d?u r?ng th�ng b�o ?� ???c g?i
                // G?i th�ng tin giao d?ch qua form
                const checkoutForm = document.getElementById('checkout-form');
                checkoutForm.querySelector('input[name="transactionId"]').value = transactionId;
                checkoutForm.querySelector('input[name="description"]').value = description;
                checkoutForm.querySelector('input[name="amount"]').value = amount;
                checkoutForm.querySelector('input[name="date"]').value = date;
                checkoutForm.querySelector('input[name="accountNumber"]').value = accountNumber;

                // Submit form
                checkoutForm.submit();
                isSuccess = true; // ?�nh d?u th�ng b�o ?� g?i
            }
            return true;
        } else {
            return false;
        }
    } catch (error) {
        console.error('ERROR', error);
        return false;
    }


}




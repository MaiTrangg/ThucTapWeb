let MY_BANK = {
    BANK_ID: 'VietinBank',
    BANK_NO: '104873782654'
};

function parseCurrency(currency) {
    // L?y t?t c? các s? t? chu?i và gi? nguyên d?u ch?m
    return currency.replace(/[^\d]/g, '');
}

function getFormattedNumberString(number) {
    return new Intl.NumberFormat('vi-VN').format(number).replace(/,/g, '.');
}

let lastCheckedTransactionId = null; // Bi?n toàn c?c ?? l?u tr? ID giao d?ch cu?i cùng ?ã ki?m tra
let notificationSent = false; // Bi?n toàn c?c ?? ??m b?o ch? g?i thông báo m?t l?n
let initialDataLength = 0; // Variable to store the initial length of data.data

document.addEventListener('DOMContentLoaded', function () {

    $('#payment').change(function (e) {
        if ($(this).val() === '3') {
            var modal = document.getElementById('qrModal');
            var img = document.getElementById('QRCODE-Img');
            var paidPrice = parseCurrency(document.getElementById('totalCost').textContent); // L?y giá tr? t? #totalCost
            var paidContent = "Chuyen Khoan"; // L?y giá tr? t? #nameConsignee
            if(!checkPhone() || !checkNote() || !checkUsername()){

            }

            // Hi?n th? modal
            modal.style.display = 'flex';

            // ??t ???ng d?n QR code
            var qrUrl = `https://img.vietqr.io/image/vietinbank-104873782654-compact2.jpg?amount=${paidPrice}&addInfo=${paidContent}&accountName=${paidContent}`;
            img.src = qrUrl;


            let checkInterval; // Bi?n toàn c?c ?? l?u ID c?a setInterval

            setTimeout(() => {
                checkInterval = setInterval(async () => {
                    const isPaid = await checkPay(paidPrice, paidContent);
                    if (isPaid) {
                        clearInterval(checkInterval); // D?ng ki?m tra khi thanh toán thành công
                    }
                }, 1000);
            }, 20000);

            // ?n scrollbar trên body
            document.body.style.overflow = 'hidden';

            // ?n scrollbar trên body
        }
    });

    // X? lý ?óng modal khi click vào nút ?óng (x)


    // X? lý ?óng modal khi click ra ngoài modal
    window.onclick = function (event) {
        var modal = document.getElementById('qrModal');
        if (event.target == modal) {
            modal.style.display = 'none';
            document.body.style.overflow = ''; // Khôi ph?c l?i scrollbar
        }
    };
});

let isSuccess = false;

async function checkPay(total, content) {
    if (isSuccess) {
        return true; // N?u thông báo ?ã ???c g?i, không c?n ki?m tra l?i
    }

    try {
        const response = await fetch('https://script.google.com/macros/s/AKfycbxpBIa6h525abwSBq3VdWg-ZAoVgf1EplAeJY4vquXIP2CvA1PNqood1nG9uGsXGSo0bQ/exec');
        const data = await response.json();

        if (initialDataLength === 0) {
            initialDataLength = data.data.length; // L?u chi?u dài ban ??u
            console.log(`Giá tr? data.data.length lúc ban ??u: ${initialDataLength}`);
        }

        const lastPay = data.data[data.data.length - 1];
        const lastTotal = lastPay['Giá tr?'];
        const lastContent = lastPay['Mô t?'];
        console.log(lastTotal, lastContent);

        // Ki?m tra n?u s? l??ng giao d?ch ?ã t?ng lên
        if (data.data.length > initialDataLength) {
            console.log(`Giá tr? data.data.length khi ?ã chuy?n kho?n thành công: ${data.data.length}`);
            if (!isSuccess) {
                alert('Thanh toán thành công');
                document.getElementById('payment').value = '3'; // ??t ph??ng th?c thanh toán thành 3
                document.getElementById('checkoutform').submit(); // G?i form
                isSuccess = true; // ?ánh d?u r?ng thông báo ?ã ???c g?i
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




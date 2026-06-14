// Theme Toggle Logic
const themeToggleBtn = document.getElementById('theme-toggle');
const htmlElement = document.documentElement;

themeToggleBtn.addEventListener('click', () => {
    const currentTheme = htmlElement.getAttribute('data-theme');
    const newTheme = currentTheme === 'dark' ? 'light' : 'dark';
    htmlElement.setAttribute('data-theme', newTheme);
    
    // Update button icon
    const icon = themeToggleBtn.querySelector('i');
    if (newTheme === 'light') {
        icon.className = 'fa-solid fa-sun';
    } else {
        icon.className = 'fa-solid fa-moon';
    }
});

// Modal Actions
function openModal(id) {
    document.getElementById(id).classList.add('active');
}

function closeModal(id) {
    document.getElementById(id).classList.remove('active');
}

// Sidebar Navigation
document.getElementById('nav-transfer').addEventListener('click', (e) => {
    e.preventDefault();
    openModal('modal-transfer');
});

// Chart.js - Spending Overview
const ctx = document.getElementById('spendingChart').getContext('2d');
new Chart(ctx, {
    type: 'doughnut',
    data: {
        labels: ['Shopping', 'Transfers', 'Bills', 'Food', 'Travel', 'Others'],
        datasets: [{
            data: [24560, 18900, 12450, 8750, 7990, 6000],
            backgroundColor: [
                '#3b82f6', // blue
                '#10b981', // green
                '#f59e0b', // warning
                '#ef4444', // danger
                '#8b5cf6', // purple
                '#6b7280'  // grey
            ],
            borderWidth: 0
        }]
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                position: 'right',
                labels: {
                    color: '#9ca3af',
                    font: {
                        family: 'Outfit',
                        size: 12
                    }
                }
            }
        },
        cutout: '70%'
    }
});

// Simulated Login/Status Fetch on Load
document.addEventListener('DOMContentLoaded', () => {
    fetchAccounts();
});

function fetchAccounts() {
    fetch('/customer/accounts')
        .then(response => {
            if (response.ok) return response.json();
            throw new Error('Not authenticated');
        })
        .then(accounts => {
            const selectEl = document.getElementById('transfer-from');
            if (selectEl) {
                selectEl.innerHTML = '';
                accounts.forEach(acc => {
                    const option = document.createElement('option');
                    option.value = acc.accountNo;
                    option.textContent = `${acc.type} - ${acc.accountNo} (₹ ${acc.balance})`;
                    selectEl.appendChild(option);
                });
            }
        })
        .catch(err => {
            console.log('User is visiting as guest or not logged in yet:', err.message);
        });
}

// Transfer Handler
document.getElementById('form-transfer').addEventListener('submit', (e) => {
    e.preventDefault();
    const fromAcc = document.getElementById('transfer-from').value;
    const toAcc = document.getElementById('transfer-to').value;
    const amount = document.getElementById('transfer-amount').value;

    fetch('/customer/transfer', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            fromAccountNo: fromAcc,
            toAccountNo: toAcc,
            amount: amount
        })
    })
    .then(res => res.json())
    .then(data => {
        if (data.txnId) {
            alert(`Transfer successful! Transaction ID: ${data.txnId}`);
            closeModal('modal-transfer');
            fetchAccounts();
        } else {
            alert(`Transfer failed: ${data.message}`);
        }
    })
    .catch(err => {
        alert(`Error executing transfer: ${err.message}`);
    });
});

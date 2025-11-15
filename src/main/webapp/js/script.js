document.addEventListener('DOMContentLoaded', function() {
    console.log('DOM loaded');
    const body = document.body;
    const header = document.querySelector('header');

    const darkTheme = () => {
            const themeBtn = document.createElement('button');
            themeBtn.textContent = '🌙';
            themeBtn.className = 'theme-toggle';
            themeBtn.style.cssText = `
                padding: 10px;
                background: grey;
                color: white;
                border: none;
                border-radius: 50%;
                cursor: pointer;
                z-index: 1000;
                width: 40px;
                height: 40px;
            `;

            themeBtn.addEventListener('click', () => {
                body.classList.toggle('dark-theme');
                themeBtn.textContent = body.classList.contains('dark-theme') ? '☀️' : '🌙';
            });

            header.appendChild(themeBtn);
        };

        const init = () => {
                darkTheme();
                console.log('All JS functions initialized');
            };

            init();
});

const menuToggle = document.getElementById('menuToggle');
const mainNav = document.getElementById('mainNav');
const overlay = document.getElementById('overlay');
const closeBtn = document.querySelector('.close-btn');

function openMenu() {
    mainNav.classList.add('active');
    overlay.classList.add('active');
    document.body.style.overflow = 'hidden';
}

function closeMenu() {
    mainNav.classList.remove('active');
    overlay.classList.remove('active');
    document.body.style.overflow = '';
}

if (menuToggle) {
    menuToggle.addEventListener('click', openMenu);
}

if (closeBtn) {
    closeBtn.addEventListener('click', closeMenu);
}

if (overlay) {
    overlay.addEventListener('click', closeMenu);
}

document.addEventListener('keydown', (e) => {
    if (e.key === 'Escape') {
        closeMenu();
    }
});

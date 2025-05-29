// Function to load HTML components
async function loadComponent(elementId, componentPath) {
  try {
    const response = await fetch(componentPath);
    const html = await response.text();
    document.getElementById(elementId).innerHTML = html;
  } catch (error) {
    console.error(`Error loading component ${componentPath}:`, error);
  }
}

// Load all components when the page loads
document.addEventListener('DOMContentLoaded', () => {
  loadComponent('navigation-menu', '/components/navigation.html');
  loadComponent('user-profile', '/components/user-profile.html');
}); 
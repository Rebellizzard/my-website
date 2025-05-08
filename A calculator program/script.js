let display = document.getElementById('display');

// Function to append numbers or operators to the display
function appendNumber(number) {
    display.value += number;
}

// Function to clear the display
function clearDisplay() {
    display.value = '';
}

// Function to calculate the result
function calculateResult() {
    try {
        display.value = eval(display.value); // Using eval to evaluate the expression
    } catch (error) {
        display.value = 'Error';
    }
}

# 🚀 Secure File & Text Sharing App

A secure, fast, and easy-to-use web application for sharing files and text with unique access codes. This app ensures secure file handling, rate limiting, and visitor count tracking while offering a clean, user-friendly interface.

## 🛠 Features

- **🔒 Secure File Upload & Retrieval**: Upload files (TXT, JPG, PNG, PDF, DOCX) securely and retrieve them using a unique 4-digit access code.
- **📝 Text Sharing & Download**: Share and retrieve text as a downloadable text file, ensuring content is transferred securely.
- **📁 File Type Restrictions**: Supports only safe and authorized file types (TXT, JPG, PNG, PDF, DOCX), preventing harmful uploads.
- **📊 Visitor Count Tracking**: Persistent visitor count tracking across sessions.
- **⚡ Rate Limiting**: Prevents abuse by limiting excessive requests per user to enhance security.

## 🧰 Technologies Used

- **Flask**: A lightweight Python web framework for backend functionality.
- **HTML/CSS**: For building the frontend interface.
- **Session Management**: Ensures persistent visitor count tracking across sessions.
- **Flask-Limiter**: Protects against abuse by enforcing rate limiting for requests.
- **Werkzeug Secure Filename**: Helps to securely handle file uploads, preventing path traversal attacks.

## 📥 Installation & Setup

To get started with this app, follow these steps:

```
# Clone the repository
git clone https://github.com/yourusername/secure-file-sharing.git
cd secure-file-sharing

# Set up a virtual environment (optional but recommended)
python -m venv venv
source venv/bin/activate  # On Windows use: venv\Scripts\activate

# Install dependencies
pip install -r requirements.txt

# Run the Flask application
python app.py

# Access the web app in your browser
http://127.0.0.1:5000/
```
## API Endpoints

1. **Upload File**  
   **Method**: POST  
   **Endpoint**: `/upload`  
   **Request**: Multipart form with a file  
   **Response**: JSON with file access code

2. **Retrieve File/Text**  
   **Method**: GET  
   **Endpoint**: `/retrieve?fileCode={code}`  
   **Response**: Download file or JSON response for text

3. **Share Text**  
   **Method**: POST  
   **Endpoint**: `/share`  
   **Request**: Form data with `sharedText`  
   **Response**: JSON with text access code

4. **Visitor Count**  
   **Method**: GET  
   **Endpoint**: `/visitor-count`  
   **Response**: JSON with visitor count

## Security Measures

- **Restricted File Types**: Only allows specific formats (TXT, JPG, PNG, PDF, DOCX)
- **Rate Limiting**: Limits API requests per user to prevent abuse
- **Session-Based Visitor Counting**: Prevents count increase on refresh
- **Secure File Handling**: Uses `secure_filename()` to avoid path traversal attacks

## Future Enhancements

- Add authentication for restricted access
- Implement database storage for better scalability
- Enhance UI with a frontend framework

## License

This project was created by me. Feel free to contribute and enhance it!


 



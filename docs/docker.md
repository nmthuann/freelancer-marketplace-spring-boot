## Tìm hiểu về Docker
- Containerized applications are more flexible and available than in **past deployment models, where applications were installed directly onto specific machines as packages deeply integrated into the host.**
- containerization là gì?
- **Containerization là quá trình đóng gói mã phần mềm, các phần phụ thuộc của nó và các cấu hình cần thiết vào một đơn vị độc lập, di động được gọi là container. Các vùng chứa này chạy nhất quán trên các môi trường khác nhau, đảm bảo phần mềm hoạt động theo cùng một cách, bất kể cơ sở hạ tầng bên dưới. Các vùng chứa nhẹ, hiệu quả và có thể dễ dàng chia sẻ cũng như triển khai, khiến chúng trở thành lựa chọn tuyệt vời cho các dự án phát triển web.**

- Docker là gì?
    - nhược điểm không đống gói, khi cài một application lên server, application đó sẽ đồi hỏi  các lib, thông tin config, file run đi kèm theo nhằm phục vụ quá trình chạy của application đó. Nếu 1 application chạy thì không vấn đề gì nhưng có rất nhiều application chạy một lúc đòi hỏi lib kèm theo → tốn tài nguyên → phức tạp → lộn xộn → xung đột phần mềm → xung đột giữa các thư viện với OS.
    - Giải quyết vấn đề:
        - Docker tạo ra môi trường độc lập khép kín kèm theo các thư viện, ctrinh con hỗ trợ → chúng ta chỉ cài môi trường độc lập này lên trên hệ thống server của chúng ta → gọi là docker container.
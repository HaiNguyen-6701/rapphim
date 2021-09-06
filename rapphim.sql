-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th9 04, 2021 lúc 02:35 PM
-- Phiên bản máy phục vụ: 10.4.20-MariaDB
-- Phiên bản PHP: 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `rapphim`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chi_tiet_hoa_don`
--

CREATE TABLE `chi_tiet_hoa_don` (
  `idPhim` int(11) NOT NULL,
  `idKhachhang` varchar(25) NOT NULL,
  `idGhe` varchar(11) NOT NULL,
  `idPhong` int(11) NOT NULL,
  `idNhanVien` varchar(25) NOT NULL,
  `soLuong` varchar(50) NOT NULL,
  `Gia` int(11) NOT NULL,
  `idhoadon` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chuc_vu`
--

CREATE TABLE `chuc_vu` (
  `IdChucVu` varchar(50) NOT NULL,
  `TenchucVu` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dinh_dang_phim`
--

CREATE TABLE `dinh_dang_phim` (
  `idDinhDang` varchar(25) NOT NULL,
  `ten` varchar(25) NOT NULL,
  `phuThu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ghe_ngoi`
--

CREATE TABLE `ghe_ngoi` (
  `idPhong` int(11) NOT NULL,
  `viTriHang` int(11) NOT NULL,
  `viTriCot` int(11) NOT NULL,
  `daChon` tinyint(1) NOT NULL,
  `idGhe` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `ghe_ngoi`
--

INSERT INTO `ghe_ngoi` (`idPhong`, `viTriHang`, `viTriCot`, `daChon`, `idGhe`) VALUES
(1, 1, 2, 0, '10'),
(2, 1, 10, 0, '100'),
(2, 2, 10, 0, '101'),
(2, 3, 10, 0, '102'),
(2, 4, 10, 0, '103'),
(2, 5, 10, 1, '104'),
(3, 1, 1, 1, '105'),
(3, 2, 1, 0, '106'),
(3, 3, 1, 1, '107'),
(3, 4, 1, 0, '108'),
(3, 5, 1, 1, '109'),
(1, 2, 2, 1, '11'),
(3, 1, 2, 0, '110'),
(3, 2, 2, 1, '111'),
(3, 3, 2, 0, '112'),
(3, 4, 2, 0, '113'),
(3, 5, 2, 1, '114'),
(3, 1, 3, 1, '115'),
(3, 2, 3, 0, '116'),
(3, 3, 3, 0, '117'),
(3, 4, 3, 0, '118'),
(3, 5, 3, 0, '119'),
(1, 3, 2, 1, '12'),
(3, 1, 4, 1, '120'),
(3, 2, 4, 0, '121'),
(3, 3, 4, 1, '122'),
(3, 4, 4, 1, '123'),
(3, 5, 4, 1, '124'),
(3, 1, 5, 1, '125'),
(3, 2, 5, 1, '126'),
(3, 3, 5, 1, '127'),
(3, 4, 5, 1, '128'),
(3, 5, 5, 0, '129'),
(1, 4, 2, 1, '13'),
(3, 1, 6, 0, '130'),
(3, 2, 6, 1, '131'),
(3, 3, 6, 1, '132'),
(3, 4, 6, 0, '133'),
(3, 5, 6, 1, '134'),
(3, 1, 7, 0, '135'),
(3, 2, 7, 0, '136'),
(3, 3, 7, 1, '137'),
(3, 4, 7, 1, '138'),
(3, 5, 7, 0, '139'),
(1, 5, 2, 1, '14'),
(3, 1, 8, 0, '140'),
(3, 2, 8, 1, '141'),
(3, 3, 8, 0, '142'),
(3, 4, 8, 1, '143'),
(3, 5, 8, 0, '144'),
(3, 1, 9, 0, '145'),
(3, 2, 9, 1, '146'),
(3, 3, 9, 1, '147'),
(3, 4, 9, 1, '148'),
(3, 5, 9, 1, '149'),
(1, 1, 3, 1, '15'),
(3, 1, 10, 1, '150'),
(3, 2, 10, 0, '151'),
(3, 3, 10, 0, '152'),
(3, 4, 10, 0, '153'),
(3, 5, 10, 0, '154'),
(1, 2, 3, 1, '16'),
(1, 3, 3, 1, '17'),
(1, 4, 3, 1, '18'),
(1, 5, 3, 0, '19'),
(1, 1, 4, 1, '20'),
(1, 2, 4, 1, '21'),
(1, 3, 4, 0, '22'),
(1, 4, 4, 0, '23'),
(1, 5, 4, 1, '24'),
(1, 1, 5, 0, '25'),
(1, 2, 5, 1, '26'),
(1, 3, 5, 1, '27'),
(1, 4, 5, 1, '28'),
(1, 5, 5, 0, '29'),
(1, 1, 6, 1, '30'),
(1, 2, 6, 1, '31'),
(1, 3, 6, 0, '32'),
(1, 4, 6, 1, '33'),
(1, 5, 6, 1, '34'),
(1, 1, 7, 1, '35'),
(1, 2, 7, 0, '36'),
(1, 3, 7, 1, '37'),
(1, 4, 7, 0, '38'),
(1, 5, 7, 1, '39'),
(1, 1, 8, 1, '40'),
(1, 2, 8, 0, '41'),
(1, 3, 8, 0, '42'),
(1, 4, 8, 0, '43'),
(1, 5, 8, 1, '44'),
(1, 1, 9, 1, '45'),
(1, 2, 9, 1, '46'),
(1, 3, 9, 1, '47'),
(1, 4, 9, 1, '48'),
(1, 5, 9, 1, '49'),
(1, 1, 1, 0, '5'),
(1, 1, 10, 0, '50'),
(1, 2, 10, 0, '51'),
(1, 3, 10, 1, '52'),
(1, 4, 10, 1, '53'),
(1, 5, 10, 1, '54'),
(2, 1, 1, 1, '55'),
(2, 2, 1, 1, '56'),
(2, 3, 1, 0, '57'),
(2, 4, 1, 1, '58'),
(2, 5, 1, 0, '59'),
(1, 2, 1, 1, '6'),
(2, 1, 2, 1, '60'),
(2, 2, 2, 0, '61'),
(2, 3, 2, 0, '62'),
(2, 4, 2, 0, '63'),
(2, 5, 2, 0, '64'),
(2, 1, 3, 1, '65'),
(2, 2, 3, 0, '66'),
(2, 3, 3, 1, '67'),
(2, 4, 3, 0, '68'),
(2, 5, 3, 1, '69'),
(1, 3, 1, 1, '7'),
(2, 1, 4, 1, '70'),
(2, 2, 4, 0, '71'),
(2, 3, 4, 0, '72'),
(2, 4, 4, 1, '73'),
(2, 5, 4, 1, '74'),
(2, 1, 5, 1, '75'),
(2, 2, 5, 1, '76'),
(2, 3, 5, 1, '77'),
(2, 4, 5, 1, '78'),
(2, 5, 5, 1, '79'),
(1, 4, 1, 0, '8'),
(2, 1, 6, 0, '80'),
(2, 2, 6, 0, '81'),
(2, 3, 6, 1, '82'),
(2, 4, 6, 0, '83'),
(2, 5, 6, 1, '84'),
(2, 1, 7, 0, '85'),
(2, 2, 7, 0, '86'),
(2, 3, 7, 1, '87'),
(2, 4, 7, 1, '88'),
(2, 5, 7, 0, '89'),
(1, 5, 1, 1, '9'),
(2, 1, 8, 1, '90'),
(2, 2, 8, 0, '91'),
(2, 3, 8, 0, '92'),
(2, 4, 8, 1, '93'),
(2, 5, 8, 0, '94'),
(2, 1, 9, 0, '95'),
(2, 2, 9, 1, '96'),
(2, 3, 9, 0, '97'),
(2, 4, 9, 1, '98'),
(2, 5, 9, 1, '99');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `idNV` varchar(25) NOT NULL,
  `idKH` varchar(25) NOT NULL,
  `TongGia` int(11) NOT NULL,
  `ngayLap` varchar(50) NOT NULL,
  `idHoaDon` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`idNV`, `idKH`, `TongGia`, `ngayLap`, `idHoaDon`) VALUES
('1', '1', 300000, '4/9/2021', '1'),
('2', '2', 400000, '12/8/2021', '2'),
('11', '4', 200000, '12/9/2021', '3'),
('1', '2', 200000, '2/9/2021', '4'),
('2', '2', 300000, '3/9/2021', '5');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khach_hang`
--

CREATE TABLE `khach_hang` (
  `idKhachHang` varchar(25) NOT NULL,
  `hoTen` varchar(25) NOT NULL,
  `soDienThoai` varchar(25) NOT NULL,
  `gioiTinh` varchar(25) NOT NULL,
  `email` varchar(25) NOT NULL,
  `matKhau` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `khach_hang`
--

INSERT INTO `khach_hang` (`idKhachHang`, `hoTen`, `soDienThoai`, `gioiTinh`, `email`, `matKhau`) VALUES
('1', 'Nguyễn Tuấn A', '9593040503', 'Nam', 'tuanhai234@gmail.com', 'hai230601'),
('2', 'Phan Thi Như', '2444435432', 'Nữ', 'Nhu123@gmail.com', 'Phannhu123'),
('3', 'Nguyễn Văn B', '02030503023', 'Nam', 'fefwfwf@gmail.com', 'phr123'),
('4', 'Sam', '0708978308', 'Nữ', 'hainguyen6701@gmail.com', '53fa048ccddb201659e94fa80df2c4e6'),
('5', 'Nguyễn Tấn Phát', '1234321086', 'Nam', 'PhatTan123@gmail.com', 'Tanphat123'),
('6', 'Bùi Thi Hoa', '29443948593', 'Nữ', 'HoaBui123@gmail.com', 'Hoabui234'),
('7', 'Bùi Văn Bình', '24949204042', 'Nam', 'Vanbinh123@gmail.com', 'Vanbinh1242'),
('8', 'Phan Thi Hoài', '1305968292', 'Nữ', 'hoai123@gmail.com', 'Hoai789'),
('9', 'Bùi Thu Hoài', '093749273932', 'Nữ', 'thuhoai@gmail.com', 'thuhoai123');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhan_vien`
--

CREATE TABLE `nhan_vien` (
  `idNhanVien` varchar(25) NOT NULL,
  `hoTen` varchar(25) NOT NULL,
  `soDienThoai` varchar(25) NOT NULL,
  `email` varchar(25) NOT NULL,
  `Chucvu` varchar(20) NOT NULL,
  `Gioitinh` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `nhan_vien`
--

INSERT INTO `nhan_vien` (`idNhanVien`, `hoTen`, `soDienThoai`, `email`, `Chucvu`, `Gioitinh`) VALUES
('1', 'Nguyễn Thu Hương', '23235433246', 'thuhuong@gmail.com', 'Nhân viên', 'Nữ'),
('10', 'Ngô Thị Xuân Hoài', '09049534237', 'xuanhoai@gmail.com', 'Nhân viên', 'Nam'),
('11', 'Lâm chí Bình', '0203050485', 'binhlam@gmail.com', 'Nhân viên', 'Nam'),
('12', 'Lâm Phong', '060940593040', 'phonglam@gmail.com', 'Nhân viên', 'Nam'),
('13', 'Tô Ngọc Bích', '53959392040', 'bichto@gmail.com', 'Nhân viên', 'Nữ'),
('14', 'Biên Thị Thu Diễm', '03059403029', 'diembien@gmail.com', 'Nhân viên', 'Nữ'),
('15', 'Mạc Tài Phến', '01020304050', 'taiphen@gmail.com', 'Nhân viên', 'Nam'),
('16', 'Bùi Xuấn Hoài', '4995403029', 'xuanhoai@gmail.com', 'Nhân viên', 'Nữ'),
('17', 'Bùi Xuấn Hoa', '32423432322', 'xuanhoai@gmail.com', 'Admin', 'Nữ'),
('18', 'Mã Văn Lâm', '2746362936', 'vanlam@gmail.com', 'Admin', 'Nam'),
('19', 'Bùi Tấn Trung', '48593920490', 'tantrung@gmail.com', 'Nhân viên', 'Nam'),
('2', 'Nguyễn Thị Xuân', '5645674324', 'thixuan@gmail.com', 'Nhân viên', 'Nữ'),
('20', 'Bùi Tấn Hảo', '493920492', 'tanhao@gmail.com', 'Nhân viên', 'Nam'),
('25', 'Cao Đỗ Hoàng Phi', '0362573471', 'caodohoangphi08@gmail.com', 'Chức vụ', 'Giới tính'),
('26', 'ưdwdw', '1234567890', 'sádasdsad', 'Nhân viên', 'Nữ'),
('3', 'Tch', '32165451', 'occho@.com', 'Admin', 'Nữ'),
('4', 'Lâm Phong Khải', '5645673232', 'phongkhai@gmail.com', 'Nhân viên', 'Nam'),
('5', 'Lâm Chấn Khang', '5645673232', 'chankhang@gmail.com', 'Nhân viên', 'Nam'),
('6', 'Ngô Hoài Kiệt', '5645673232', 'chankhang@gmail.com', 'Nhân viên', 'Nam'),
('69', 'Trần Ngọc Trâm ', '2349594030', 'Ngoctram@gmail.com', 'Nhân viên', 'Nữ'),
('7', 'Doãn Chí Bình', '56456733413', 'chibinh@gmail.com', 'Nhân viên', 'Nam'),
('8', 'Tô Thị Việt Thu', '248575930499', 'vietthuto@gmail.com', 'Nhân viên', 'Nữ'),
('9', 'Biên Thị Thanh', '34323134322', 'vietthuto@gmail.com', 'Nhân viên', 'Nữ'),
('96', 'jowe', '4348382983', 'knksnkdnksdnks', 'Nhân viên', 'Nam');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phim`
--

CREATE TABLE `phim` (
  `idPhim` int(11) NOT NULL,
  `tenPhim` varchar(50) NOT NULL,
  `theLoai` varchar(50) NOT NULL,
  `thoiLuong` int(11) NOT NULL,
  `ngonNgu` varchar(50) NOT NULL,
  `dienVien` varchar(50) NOT NULL,
  `quocGia` varchar(25) NOT NULL,
  `nhaSanXuat` varchar(50) NOT NULL,
  `Ngaycongchieu` varchar(30) DEFAULT NULL,
  `giaVe` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `phim`
--

INSERT INTO `phim` (`idPhim`, `tenPhim`, `theLoai`, `thoiLuong`, `ngonNgu`, `dienVien`, `quocGia`, `nhaSanXuat`, `Ngaycongchieu`, `giaVe`) VALUES
(1, 'FAST AND FURIOUS 9', 'Hành động / Phiêu lưu', 160, 'Tiếng Anh', 'Vin Diesel,John Cena, Michelle Rodriguez, Tyrese G', 'Mỹ', 'Neal H. Moritz, Vin Diesel', '01-04-2021', '100.000VND'),
(2, 'GODZILLA & KING KONG', 'Hành động / Viễn tưởng', 113, 'Tiếng Anh', 'Millie Bobby Brown,Alexander Skarsgård,Rebecca Hal', 'Mỹ', 'Warner Bros', '26-03-2021', '100.000VND'),
(3, 'LẬT MẶT: 48H', 'Hành động', 110, 'Việt nam', 'Ốc Thanh Vân, Huỳnh Đông, Mạc Văn Khoa, Võ Thành T', 'Việt Nam', 'Lý Hải', '14-04-2021', '100.000VND'),
(4, 'THÁM TỪ LỪNG DANH CONAN: VIÊN ĐẠN ĐỎ', 'Hành động, Hoạt hình, Hồi hộp,', 111, 'Tiếng Nhật - Phụ đề Tiếng Việt', 'Minami Hamabe, Megumi Hayashibara, Ogata Kenichi, ', 'Nhật Bản', 'Tomoka Nagaoka', '23-04-2021', '100.000VND'),
(5, 'ONG NHÍ PHIÊU LƯ KÝ', 'Hoạt hình', 88, 'Tiếng Anh - Phụ đề tiếng Việt', 'Coco Jack Gillies, Evie Gillies, Benson Jack Antho', 'Anh', 'Noel Cleary', '21-04-2021', '100.000VND'),
(6, 'MORTAL KOMBAT: CUỘC CHIẾN SINH TỬ  ', 'Hành động', 110, 'Tiếng Anh - Phụ đề tiếng Việt', 'Sanada Hiroyuki, Lewis Tan, Jessica McNamee ', 'Mỹ', 'Simon McQuoid ', '09-04-2021', '100.000VND'),
(7, 'THE UNHOLY: ẤN QUỶ  ', 'Kinh dị', 100, 'Tiếng Anh - Phụ đề tiếng Việt', 'Cary Elwes, Jeffrey Dean Morgan ', 'Mỹ', 'Ghost House Pictures ', '23-06-2021', '100.000VND'),
(8, 'THE HYPNOSIS: CON LẮC TÀ THUẬT ', 'Kinh dị', 86, 'Hàn Quốc - Phụ đề tiếng Việt', 'David Lee, Shin Ji Won ', 'Hàn Quốc', 'Choi Jae Hoon  ', '17-09-2021', '100.000VND'),
(9, 'SEOBOK: NGƯỜI NHÂN BẢN ', 'Ly Kì, Hành Động, Giả Tưởng  ', 114, 'Hàn Quốc - Phụ đề tiếng Việt', 'Gong Yoo, Park Bo Gum  ', 'Hàn Quốc', 'CJ Entertainment  ', '14-05-2021', '100.000VND'),
(10, 'KIỀU', 'Tâm Lý', 90, 'Tiếng Việt', 'Trình Mỹ Duyên, NSND Lê Khanh, Cao Thái Hà ', 'Việt Nam', 'Mai Thu Huyền', '05-05-2021', '100.000VND'),
(11, 'BÀN TAY DIỆT QUỶ', 'Hành Động, Kinh Dị', 128, 'Hàn Quốc - Phụ đề tiếng Việt', 'Park Seo Joon, Ahn Sung Ki, Woo Do Hwan', 'Hàn Quốc', 'Kim Joo Hwan', '31-05-2021', '100.000VND'),
(12, 'BẠN TÔI', 'Tâm Lý, Tình cảm', 125, 'Tiếng Anh - Phụ đề tiếng Việt', 'Jason Segel, Dakota Johnson, Casey Affleck', 'Mỹ', 'Gabriela Cowperthwaite', '30-02-2021', '100.000VND'),
(13, 'RAYA VÀ RỒNG THẦN CUỐI CÙNG', 'Hoạt Hình', 114, 'Tiếng Anh với phụ đề tiếng Việt và lồng tiếng Việt', 'Awkwafina, Kelly Marie Tran', 'Anh', 'Don Hall, Carlos López Estrada', '25-05-2021', '100.000VND'),
(14, 'ROUND TABLE: CAMELOT', 'Hoạt Hình 13+', 90, 'Tiếng Nhật - Phụ đề tiếng Việt', 'Mamoru Miyano, Nobunaga Shimazaki, Rie Takahashi', 'Nhật Bản', 'Kei Suezawa', '30-04-2021', '100.000VND'),
(15, 'JOSEE: KHI NÀNG THƠ YÊU', 'Hoạt Hình', 98, 'Tiếng Nhật - Phụ đề tiếng Việt', 'Satoshi Tsumabuki , Chizuru Ikewaki', 'Nhật Bản', 'Kotaro Tamura', '30-04-2021', '100.000VND'),
(16, 'BỐ GIÀ', 'Gia đình, Hài', 128, 'Tiếng Việt', 'Trấn Thành, Tuấn Trần, Ngân Chi, NSND Ngọc Giàu, L', 'Việt Nam', 'Trấn Thành', '06-05-2021', '100.000VND'),
(17, 'CRUELLA  ', 'Hài, Tội Phạm  ', 120, 'Tiếng Anh - Phụ đề tiếng Việt', 'Emma Stone, Emma Thompson, Mark Strong  ', 'Mỹ', 'TSG Entertainment  ', '06-05-2021', '100.000VND'),
(18, 'BẪY NGỌT NGÀO', 'Tâm Lý', 126, 'Tiếng Việt', 'Bảo Anh, Minh Hằng, Diệu Nhi, Thuận Nguyễn', 'Việt Nam', 'Đinh Hà Uyên Thư', '10-05-2021', '100.000VND'),
(19, 'VÙNG ĐẤT CÂM LẶNG II', 'Kinh dị', 120, 'Tiếng Anh - Phụ đề tiếng Việt', 'Cillian Murphy, Emily Blunt, Noah Jupe', 'Mỹ', 'John Krasinski', '11-05-2021', '100.000VND'),
(20, 'BLACK WIDOW GÓA PHỤ ĐEN  ', 'Hành Động, Giả Tưởng, Phiêu Lư', 132, 'Tiếng Anh - Phụ đề tiếng Việt', 'Scarlett Johansson, Florence Pugh, Rachel Weisz  ', 'Mỹ', 'Marvel Studios  ', '20-05-2021', '100.000VND'),
(21, 'THE KING\'S MAN KINGSMAN: KHỞI NGUỒN  ', 'Hành động', 133, 'Tiếng Anh - Phụ đề tiếng Việt', 'Gemma Arterton, Harris Dickinson, Ralph Fiennes  ', 'Anh', 'Matthew Vaughn  ', '16-05-2021', '100.000VND'),
(22, 'NO TIME TO DIE KHÔNG PHẢI LÚC CHẾT ', 'Hành Động, Phiêu Lưu, Ly Kì  ', 142, 'Tiếng Anh - Phụ đề tiếng Việt', 'Daniel Craig, Rami Malek, Naomie Harris, Lea Seydo', 'Mỹ', 'Metro-Goldwyn-Mayer (MGM)  ', '01-04-2021', '100.000VND'),
(23, 'SHANG-CHI AND THE LEGEND OF THE TEN RINGS ', 'Hành Động ', 112, 'Tiếng Anh - Phụ đề tiếng Việt', 'Simi Liu, Lương Triều Vỹ, Awkwafina ', 'Mỹ', 'Walt Disney Pictures ', '14-05-2021', '100.000VND'),
(24, 'CANDYMAN SÁT NHÂN TRONG GƯƠNG  ', 'Kinh dị,ly kì', 115, 'Tiếng Anh - Phụ đề tiếng Việt', 'Yahya Abdul-Mateen II, Teyonah Parris  ', 'Mỹ', 'Nia DaCosta ', '23-05-2021', '100.000VND'),
(25, 'ĐÊM TRÓI BUỘC', 'Hồi hộp, Kinh Dị', 110, 'Tiếng Ba Tư & Tiếng Anh - Phụ đề Tiếng Việt', 'Shahab Hosseini, Niousha Noor, Leah Oganyan', 'Mỹ', 'Kourosh Ahari', '14-05-2021', '100.000VND'),
(34, 'King kong', 'hành động', 214, 'tiếng anh', 'abc', 'Việt Nam', 'Hainguyen', '24-7-2021', '100000'),
(96, 'kẻ cắp không gian', 'Hành Động', 123, 'tiếng việt', 'Trấn Thành,Trường Giang', 'Việt Nam', 'Tôn đông á', '09-05-2021', '100000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phong_chieu`
--

CREATE TABLE `phong_chieu` (
  `idPhong` int(11) NOT NULL,
  `soLuongHang` int(11) NOT NULL,
  `soLuongCot` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `phong_chieu`
--

INSERT INTO `phong_chieu` (`idPhong`, `soLuongHang`, `soLuongCot`) VALUES
(1, 5, 10),
(2, 5, 10),
(3, 5, 10);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `suat_chieu`
--

CREATE TABLE `suat_chieu` (
  `gioBatDau` time NOT NULL,
  `gioKetThuc` time NOT NULL,
  `ngayChieu` date NOT NULL,
  `idPhongChieu` int(11) NOT NULL,
  `idDinhDang` varchar(25) NOT NULL,
  `idSuatChieu` int(11) NOT NULL,
  `idPhim` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `IdNV` int(11) NOT NULL,
  `tenDangNhap` varchar(50) NOT NULL,
  `mKhau` varchar(50) NOT NULL,
  `TenNV` varchar(50) NOT NULL,
  `Chucvu` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`IdNV`, `tenDangNhap`, `mKhau`, `TenNV`, `Chucvu`) VALUES
(3, 'hai2301', 'tuanhai2301', 'Nguyễn Tuấn Hải', 'Admin'),
(1, 'nhanvien', '123', 'hai', 'nhân viên'),
(2, 'nhanvien2', '456', 'Phát', 'Admin'),
(4, 'nhanvien1', '789', 'Phi', 'Admin'),
(5, 'nhanvien3', '12345', 'Phú', 'Admin'),
(6, 'Steve', '12345', 'Steve', 'Nhân viên'),
(7, 'hoangphi', '1234', 'Hoàng Phi', 'Admin'),
(8, 'phat', '12344', 'Nhật Phát', 'Nhân viên'),
(9, 'Nam', '1245', 'Lý Nam', 'Nhân viên');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ve_ban`
--

CREATE TABLE `ve_ban` (
  `idVe` int(25) NOT NULL,
  `ngayBan` date NOT NULL,
  `thanhTien` int(11) NOT NULL,
  `idSuatChieu` int(11) NOT NULL,
  `idGiaVe` int(11) NOT NULL,
  `idGhe` varchar(11) NOT NULL,
  `idNhanVien` varchar(25) NOT NULL,
  `idKhachHang` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chi_tiet_hoa_don`
--
ALTER TABLE `chi_tiet_hoa_don`
  ADD KEY `idhoadon` (`idhoadon`),
  ADD KEY `idKhachhang` (`idKhachhang`),
  ADD KEY `idGhe` (`idGhe`),
  ADD KEY `idPhim` (`idPhim`),
  ADD KEY `idNhanVien` (`idNhanVien`),
  ADD KEY `idPhong` (`idPhong`);

--
-- Chỉ mục cho bảng `chuc_vu`
--
ALTER TABLE `chuc_vu`
  ADD PRIMARY KEY (`IdChucVu`);

--
-- Chỉ mục cho bảng `dinh_dang_phim`
--
ALTER TABLE `dinh_dang_phim`
  ADD PRIMARY KEY (`idDinhDang`);

--
-- Chỉ mục cho bảng `ghe_ngoi`
--
ALTER TABLE `ghe_ngoi`
  ADD PRIMARY KEY (`idGhe`),
  ADD KEY `idPhong` (`idPhong`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`idHoaDon`),
  ADD KEY `idNV` (`idNV`),
  ADD KEY `idKH` (`idKH`);

--
-- Chỉ mục cho bảng `khach_hang`
--
ALTER TABLE `khach_hang`
  ADD PRIMARY KEY (`idKhachHang`);

--
-- Chỉ mục cho bảng `nhan_vien`
--
ALTER TABLE `nhan_vien`
  ADD PRIMARY KEY (`idNhanVien`),
  ADD KEY `idChucVu` (`Chucvu`);

--
-- Chỉ mục cho bảng `phim`
--
ALTER TABLE `phim`
  ADD PRIMARY KEY (`idPhim`);

--
-- Chỉ mục cho bảng `phong_chieu`
--
ALTER TABLE `phong_chieu`
  ADD PRIMARY KEY (`idPhong`);

--
-- Chỉ mục cho bảng `suat_chieu`
--
ALTER TABLE `suat_chieu`
  ADD PRIMARY KEY (`idSuatChieu`),
  ADD KEY `suat_chieu_ibfk_1` (`idPhongChieu`),
  ADD KEY `idDinhDang` (`idDinhDang`),
  ADD KEY `idPhim` (`idPhim`);

--
-- Chỉ mục cho bảng `ve_ban`
--
ALTER TABLE `ve_ban`
  ADD PRIMARY KEY (`idVe`),
  ADD KEY `idGiaVe` (`idGiaVe`),
  ADD KEY `idGhe` (`idGhe`),
  ADD KEY `idNhanVien` (`idNhanVien`),
  ADD KEY `idKhachHang` (`idKhachHang`),
  ADD KEY `idSuatChieu` (`idSuatChieu`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `phim`
--
ALTER TABLE `phim`
  MODIFY `idPhim` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=97;

--
-- AUTO_INCREMENT cho bảng `suat_chieu`
--
ALTER TABLE `suat_chieu`
  MODIFY `idSuatChieu` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `ve_ban`
--
ALTER TABLE `ve_ban`
  MODIFY `idVe` int(25) NOT NULL AUTO_INCREMENT;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chi_tiet_hoa_don`
--
ALTER TABLE `chi_tiet_hoa_don`
  ADD CONSTRAINT `chi_tiet_hoa_don_ibfk_1` FOREIGN KEY (`idhoadon`) REFERENCES `hoadon` (`idHoaDon`),
  ADD CONSTRAINT `chi_tiet_hoa_don_ibfk_2` FOREIGN KEY (`idKhachhang`) REFERENCES `khach_hang` (`idKhachHang`),
  ADD CONSTRAINT `chi_tiet_hoa_don_ibfk_4` FOREIGN KEY (`idPhim`) REFERENCES `phim` (`idPhim`),
  ADD CONSTRAINT `chi_tiet_hoa_don_ibfk_5` FOREIGN KEY (`idNhanVien`) REFERENCES `nhan_vien` (`idNhanVien`),
  ADD CONSTRAINT `chi_tiet_hoa_don_ibfk_6` FOREIGN KEY (`idPhong`) REFERENCES `phong_chieu` (`idPhong`),
  ADD CONSTRAINT `chi_tiet_hoa_don_ibfk_7` FOREIGN KEY (`idGhe`) REFERENCES `ghe_ngoi` (`idGhe`);

--
-- Các ràng buộc cho bảng `ghe_ngoi`
--
ALTER TABLE `ghe_ngoi`
  ADD CONSTRAINT `ghe_ngoi_ibfk_1` FOREIGN KEY (`idPhong`) REFERENCES `phong_chieu` (`idPhong`);

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`idNV`) REFERENCES `nhan_vien` (`idNhanVien`),
  ADD CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`idKH`) REFERENCES `khach_hang` (`idKhachHang`);

--
-- Các ràng buộc cho bảng `suat_chieu`
--
ALTER TABLE `suat_chieu`
  ADD CONSTRAINT `suat_chieu_ibfk_1` FOREIGN KEY (`idPhim`) REFERENCES `phim` (`idPhim`),
  ADD CONSTRAINT `suat_chieu_ibfk_2` FOREIGN KEY (`idDinhDang`) REFERENCES `dinh_dang_phim` (`idDinhDang`),
  ADD CONSTRAINT `suat_chieu_ibfk_3` FOREIGN KEY (`idPhongChieu`) REFERENCES `phong_chieu` (`idPhong`);

--
-- Các ràng buộc cho bảng `ve_ban`
--
ALTER TABLE `ve_ban`
  ADD CONSTRAINT `ve_ban_ibfk_2` FOREIGN KEY (`idNhanVien`) REFERENCES `nhan_vien` (`idNhanVien`),
  ADD CONSTRAINT `ve_ban_ibfk_3` FOREIGN KEY (`idKhachHang`) REFERENCES `khach_hang` (`idKhachHang`),
  ADD CONSTRAINT `ve_ban_ibfk_4` FOREIGN KEY (`idSuatChieu`) REFERENCES `suat_chieu` (`idSuatChieu`),
  ADD CONSTRAINT `ve_ban_ibfk_5` FOREIGN KEY (`idGhe`) REFERENCES `ghe_ngoi` (`idGhe`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

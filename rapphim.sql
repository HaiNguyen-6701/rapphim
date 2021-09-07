-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th9 07, 2021 lúc 09:21 PM
-- Phiên bản máy phục vụ: 10.4.18-MariaDB
-- Phiên bản PHP: 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `rapphim1`
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

--
-- Đang đổ dữ liệu cho bảng `chi_tiet_hoa_don`
--

INSERT INTO `chi_tiet_hoa_don` (`idPhim`, `idKhachhang`, `idGhe`, `idPhong`, `idNhanVien`, `soLuong`, `Gia`, `idhoadon`) VALUES
(6, '6', 'E5', 2, '1', '1', 50000, '6'),
(2, '3', 'A3', 2, '1', '1', 30000, '9'),
(2, '3', 'A3', 2, '1', '1', 30000, '9'),
(2, '3', 'A3', 2, '1', '1', 30000, '9'),
(2, '5', 'E7', 2, '2', '1', 70000, '8'),
(2, '5', 'E7', 2, '2', '1', 70000, '8'),
(2, '5', 'E7', 2, '2', '1', 70000, '8'),
(2, '10', 'E5', 2, '2', '1', 80000, '12'),
(2, '10', 'E5', 2, '2', '1', 80000, '12'),
(2, '10', 'E5', 2, '2', '1', 80000, '12'),
(2, '10', 'E5', 2, '2', '1', 80000, '12'),
(3, '10', 'E7', 4, '2', '1', 90000, '12'),
(3, '10', 'E7', 4, '2', '1', 90000, '12'),
(3, '10', 'E7', 4, '2', '1', 90000, '12');

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
  `viTriHang` varchar(11) NOT NULL,
  `viTriCot` int(11) NOT NULL,
  `daChon` tinyint(1) NOT NULL,
  `idGhe` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `ghe_ngoi`
--

INSERT INTO `ghe_ngoi` (`idPhong`, `viTriHang`, `viTriCot`, `daChon`, `idGhe`) VALUES
(1, 'A', 1, 0, 'A1'),
(1, 'A', 10, 0, 'A10'),
(1, 'A', 2, 0, 'A2'),
(1, 'A', 3, 0, 'A3'),
(1, 'A', 4, 0, 'A4'),
(1, 'A', 5, 0, 'A5'),
(1, 'A', 6, 0, 'A6'),
(1, 'A', 7, 0, 'A7'),
(1, 'A', 8, 0, 'A8'),
(1, 'A', 9, 0, 'A9'),
(1, 'B', 1, 0, 'B1'),
(1, 'B', 10, 0, 'B10'),
(1, 'B', 2, 0, 'B2'),
(1, 'B', 3, 0, 'B3'),
(1, 'B', 4, 0, 'B4'),
(1, 'B', 5, 0, 'B5'),
(1, 'B', 6, 0, 'B6'),
(1, 'B', 7, 0, 'B7'),
(1, 'B', 8, 0, 'B8'),
(1, 'B', 9, 0, 'B9'),
(1, 'C', 1, 0, 'C1'),
(1, 'C', 10, 0, 'C10'),
(1, 'C', 2, 0, 'C2'),
(1, 'C', 3, 0, 'C3'),
(1, 'C', 4, 0, 'C4'),
(1, 'C', 5, 0, 'C5'),
(1, 'C', 6, 0, 'C6'),
(1, 'C', 7, 0, 'C7'),
(1, 'C', 8, 0, 'C8'),
(1, 'C', 9, 0, 'C9'),
(1, 'D', 1, 0, 'D1'),
(1, 'D', 10, 0, 'D10'),
(1, 'D', 2, 0, 'D2'),
(1, 'D', 3, 0, 'D3'),
(1, 'D', 4, 0, 'D4'),
(1, 'D', 5, 0, 'D5'),
(1, 'D', 6, 0, 'D6'),
(1, 'D', 7, 0, 'D7'),
(1, 'D', 8, 0, 'D8'),
(1, 'D', 9, 0, 'D9'),
(1, 'E', 1, 0, 'E1'),
(1, 'E', 10, 0, 'E10'),
(1, 'E', 2, 0, 'E2'),
(1, 'E', 3, 0, 'E3'),
(1, 'E', 4, 0, 'E4'),
(1, 'E', 5, 0, 'E5'),
(1, 'E', 6, 0, 'E6'),
(1, 'E', 7, 0, 'E7'),
(1, 'E', 8, 0, 'E8'),
(1, 'E', 9, 0, 'E9'),
(1, 'F', 1, 0, 'F1'),
(1, 'F', 10, 0, 'F10'),
(1, 'F', 2, 0, 'F2'),
(1, 'F', 3, 0, 'F3'),
(1, 'F', 4, 0, 'F4'),
(1, 'F', 5, 0, 'F5'),
(1, 'F', 6, 0, 'F6'),
(1, 'F', 7, 0, 'F7'),
(1, 'F', 8, 0, 'F8'),
(1, 'F', 9, 0, 'F9'),
(1, 'G', 1, 0, 'G1'),
(1, 'G', 10, 0, 'G10'),
(1, 'G', 2, 0, 'G2'),
(1, 'G', 3, 0, 'G3'),
(1, 'G', 4, 0, 'G4'),
(1, 'G', 5, 0, 'G5'),
(1, 'G', 6, 0, 'G6'),
(1, 'G', 7, 0, 'G7'),
(1, 'G', 8, 0, 'G8'),
(1, 'G', 9, 0, 'G9'),
(2, 'A', 1, 0, 'A1'),
(2, 'A', 2, 0, 'A2'),
(2, 'A', 3, 0, 'A3'),
(2, 'A', 4, 0, 'A4'),
(2, 'A', 5, 0, 'A5'),
(2, 'A', 6, 0, 'A6'),
(2, 'A', 7, 0, 'A7'),
(2, 'A', 8, 0, 'A8'),
(2, 'A', 9, 0, 'A9'),
(2, 'A', 10, 0, 'A10'),
(2, 'B', 1, 0, 'B1'),
(2, 'B', 2, 0, 'B2'),
(2, 'B', 3, 0, 'B3'),
(2, 'B', 4, 0, 'B4'),
(2, 'B', 5, 0, 'B5'),
(2, 'B', 6, 0, 'B6'),
(2, 'B', 7, 0, 'B7'),
(2, 'B', 8, 0, 'B8'),
(2, 'B', 9, 0, 'B9'),
(2, 'B', 10, 0, 'B10'),
(2, 'C', 1, 0, 'C1'),
(2, 'C', 2, 0, 'C2'),
(2, 'C', 3, 0, 'C3'),
(2, 'C', 4, 0, 'C4'),
(2, 'C', 5, 0, 'C5'),
(2, 'C', 6, 0, 'C6'),
(2, 'C', 7, 0, 'C7'),
(2, 'C', 8, 0, 'C8'),
(2, 'C', 9, 0, 'C9'),
(2, 'C', 10, 0, 'C10'),
(2, 'D', 1, 0, 'D1'),
(2, 'D', 2, 0, 'D2'),
(2, 'D', 3, 0, 'D3'),
(2, 'D', 4, 0, 'D4'),
(2, 'D', 5, 0, 'D5'),
(2, 'D', 6, 0, 'D6'),
(2, 'D', 7, 0, 'D7'),
(2, 'D', 8, 0, 'D8'),
(2, 'D', 9, 0, 'D9'),
(2, 'D', 10, 0, 'D10'),
(2, 'E', 1, 0, 'E1'),
(2, 'E', 2, 0, 'E2'),
(2, 'E', 3, 0, 'E3'),
(2, 'E', 4, 0, 'E4'),
(2, 'E', 5, 0, 'E5'),
(2, 'E', 6, 0, 'E6'),
(2, 'E', 7, 0, 'E7'),
(2, 'E', 8, 0, 'E8'),
(2, 'E', 9, 0, 'E9'),
(2, 'E', 10, 0, 'E10'),
(2, 'F', 1, 0, 'F1'),
(2, 'F', 2, 0, 'F2'),
(2, 'F', 3, 0, 'F3'),
(2, 'F', 4, 0, 'F4'),
(2, 'F', 5, 0, 'F5'),
(2, 'F', 6, 0, 'F6'),
(2, 'F', 7, 0, 'F7'),
(2, 'F', 8, 0, 'F8'),
(2, 'F', 9, 0, 'F9'),
(2, 'F', 10, 0, 'F10'),
(2, 'G', 1, 0, 'G1'),
(2, 'G', 2, 0, 'G2'),
(2, 'G', 3, 0, 'G3'),
(2, 'G', 4, 0, 'G4'),
(2, 'G', 5, 0, 'G5'),
(2, 'G', 6, 0, 'G6'),
(2, 'G', 7, 0, 'G7'),
(2, 'G', 8, 0, 'G8'),
(2, 'G', 9, 0, 'G9'),
(2, 'G', 10, 0, 'G10'),
(3, 'A', 1, 0, 'A1'),
(3, 'A', 2, 0, 'A2'),
(3, 'A', 3, 0, 'A3'),
(3, 'A', 4, 0, 'A4'),
(3, 'A', 5, 0, 'A5'),
(3, 'A', 6, 0, 'A6'),
(3, 'A', 7, 0, 'A7'),
(3, 'A', 8, 0, 'A8'),
(3, 'A', 9, 0, 'A9'),
(3, 'A', 10, 0, 'A10'),
(3, 'B', 1, 0, 'B1'),
(3, 'B', 2, 0, 'B2'),
(3, 'B', 3, 0, 'B3'),
(3, 'B', 4, 0, 'B4'),
(3, 'B', 5, 0, 'B5'),
(3, 'B', 6, 0, 'B6'),
(3, 'B', 7, 0, 'B7'),
(3, 'B', 8, 0, 'B8'),
(3, 'B', 9, 0, 'B9'),
(3, 'B', 10, 0, 'B10'),
(3, 'C', 1, 0, 'C1'),
(3, 'C', 2, 0, 'C2'),
(3, 'C', 3, 0, 'C3'),
(3, 'C', 4, 0, 'C4'),
(3, 'C', 5, 0, 'C5'),
(3, 'C', 6, 0, 'C6'),
(3, 'C', 7, 0, 'C7'),
(3, 'C', 8, 0, 'C8'),
(3, 'C', 9, 0, 'C9'),
(3, 'C', 10, 0, 'C10'),
(3, 'D', 1, 0, 'D1'),
(3, 'D', 2, 0, 'D2'),
(3, 'D', 3, 0, 'D3'),
(3, 'D', 4, 0, 'D4'),
(3, 'D', 5, 0, 'D5'),
(3, 'D', 6, 0, 'D6'),
(3, 'D', 7, 0, 'D7'),
(3, 'D', 8, 0, 'D8'),
(3, 'D', 9, 0, 'D9'),
(3, 'D', 10, 0, 'D10'),
(3, 'E', 1, 0, 'E1'),
(3, 'E', 2, 0, 'E2'),
(3, 'E', 3, 0, 'E3'),
(3, 'E', 4, 0, 'E4'),
(3, 'E', 5, 0, 'E5'),
(3, 'E', 6, 0, 'E6'),
(3, 'E', 7, 0, 'E7'),
(3, 'E', 8, 0, 'E8'),
(3, 'E', 9, 0, 'E9'),
(3, 'E', 10, 0, 'E10'),
(3, 'F', 1, 0, 'F1'),
(3, 'F', 2, 0, 'F2'),
(3, 'F', 3, 0, 'F3'),
(3, 'F', 4, 0, 'F4'),
(3, 'F', 5, 0, 'F5'),
(3, 'F', 6, 0, 'F6'),
(3, 'F', 7, 0, 'F7'),
(3, 'F', 8, 0, 'F8'),
(3, 'F', 9, 0, 'F9'),
(3, 'F', 10, 0, 'F10'),
(3, 'G', 1, 0, 'G1'),
(3, 'G', 2, 0, 'G2'),
(3, 'G', 3, 0, 'G3'),
(3, 'G', 4, 0, 'G4'),
(3, 'G', 5, 0, 'G5'),
(3, 'G', 6, 0, 'G6'),
(3, 'G', 7, 0, 'G7'),
(3, 'G', 8, 0, 'G8'),
(3, 'G', 9, 0, 'G9'),
(3, 'G', 10, 0, 'G10'),
(2, 'A', 1, 0, 'A1'),
(2, 'A', 2, 0, 'A2'),
(2, 'A', 3, 0, 'A3'),
(2, 'A', 4, 0, 'A4'),
(2, 'A', 5, 0, 'A5'),
(2, 'A', 6, 0, 'A6'),
(2, 'A', 7, 0, 'A7'),
(2, 'A', 8, 0, 'A8'),
(2, 'A', 9, 0, 'A9'),
(2, 'A', 10, 0, 'A10'),
(2, 'B', 1, 0, 'B1'),
(2, 'B', 2, 0, 'B2'),
(2, 'B', 3, 0, 'B3'),
(2, 'B', 4, 0, 'B4'),
(2, 'B', 5, 0, 'B5'),
(2, 'B', 6, 0, 'B6'),
(2, 'B', 7, 0, 'B7'),
(2, 'B', 8, 0, 'B8'),
(2, 'B', 9, 0, 'B9'),
(2, 'B', 10, 0, 'B10'),
(2, 'C', 1, 0, 'C1'),
(2, 'C', 2, 0, 'C2'),
(2, 'C', 3, 0, 'C3'),
(2, 'C', 4, 0, 'C4'),
(2, 'C', 5, 0, 'C5'),
(2, 'C', 6, 0, 'C6'),
(2, 'C', 7, 0, 'C7'),
(2, 'C', 8, 0, 'C8'),
(2, 'C', 9, 0, 'C9'),
(2, 'C', 10, 0, 'C10'),
(2, 'D', 1, 0, 'D1'),
(2, 'D', 2, 0, 'D2'),
(2, 'D', 3, 0, 'D3'),
(2, 'D', 4, 0, 'D4'),
(2, 'D', 5, 0, 'D5'),
(2, 'D', 6, 0, 'D6'),
(2, 'D', 7, 0, 'D7'),
(2, 'D', 8, 0, 'D8'),
(2, 'D', 9, 0, 'D9'),
(2, 'D', 10, 0, 'D10'),
(2, 'E', 1, 0, 'E1'),
(2, 'E', 2, 0, 'E2'),
(2, 'E', 3, 0, 'E3'),
(2, 'E', 4, 0, 'E4'),
(2, 'E', 5, 0, 'E5'),
(2, 'E', 6, 0, 'E6'),
(2, 'E', 7, 0, 'E7'),
(2, 'E', 8, 0, 'E8'),
(2, 'E', 9, 0, 'E9'),
(2, 'E', 10, 0, 'E10'),
(2, 'F', 1, 0, 'F1'),
(2, 'F', 2, 0, 'F2'),
(2, 'F', 3, 0, 'F3'),
(2, 'F', 4, 0, 'F4'),
(2, 'F', 5, 0, 'F5'),
(2, 'F', 6, 0, 'F6'),
(2, 'F', 7, 0, 'F7'),
(2, 'F', 8, 0, 'F8'),
(2, 'F', 9, 0, 'F9'),
(2, 'F', 10, 0, 'F10'),
(2, 'G', 1, 0, 'G1'),
(2, 'G', 2, 0, 'G2'),
(2, 'G', 3, 0, 'G3'),
(2, 'G', 4, 0, 'G4'),
(2, 'G', 5, 0, 'G5'),
(2, 'G', 6, 0, 'G6'),
(2, 'G', 7, 0, 'G7'),
(2, 'G', 8, 0, 'G8'),
(2, 'G', 9, 0, 'G9'),
(2, 'G', 10, 0, 'G10'),
(3, 'A', 1, 0, 'A1'),
(3, 'A', 2, 0, 'A2'),
(3, 'A', 3, 0, 'A3'),
(3, 'A', 4, 0, 'A4'),
(3, 'A', 5, 0, 'A5'),
(3, 'A', 6, 0, 'A6'),
(3, 'A', 7, 0, 'A7'),
(3, 'A', 8, 0, 'A8'),
(3, 'A', 9, 0, 'A9'),
(3, 'A', 10, 0, 'A10'),
(3, 'B', 1, 0, 'B1'),
(3, 'B', 2, 0, 'B2'),
(3, 'B', 3, 0, 'B3'),
(3, 'B', 4, 0, 'B4'),
(3, 'B', 5, 0, 'B5'),
(3, 'B', 6, 0, 'B6'),
(3, 'B', 7, 0, 'B7'),
(3, 'B', 8, 0, 'B8'),
(3, 'B', 9, 0, 'B9'),
(3, 'B', 10, 0, 'B10'),
(3, 'C', 1, 0, 'C1'),
(3, 'C', 2, 0, 'C2'),
(3, 'C', 3, 0, 'C3'),
(3, 'C', 4, 0, 'C4'),
(3, 'C', 5, 0, 'C5'),
(3, 'C', 6, 0, 'C6'),
(3, 'C', 7, 0, 'C7'),
(3, 'C', 8, 0, 'C8'),
(3, 'C', 9, 0, 'C9'),
(3, 'C', 10, 0, 'C10'),
(3, 'D', 1, 0, 'D1'),
(3, 'D', 2, 0, 'D2'),
(3, 'D', 3, 0, 'D3'),
(3, 'D', 4, 0, 'D4'),
(3, 'D', 5, 0, 'D5'),
(3, 'D', 6, 0, 'D6'),
(3, 'D', 7, 0, 'D7'),
(3, 'D', 8, 0, 'D8'),
(3, 'D', 9, 0, 'D9'),
(3, 'D', 10, 0, 'D10'),
(3, 'E', 1, 0, 'E1'),
(3, 'E', 2, 0, 'E2'),
(3, 'E', 3, 0, 'E3'),
(3, 'E', 4, 0, 'E4'),
(3, 'E', 5, 0, 'E5'),
(3, 'E', 6, 0, 'E6'),
(3, 'E', 7, 0, 'E7'),
(3, 'E', 8, 0, 'E8'),
(3, 'E', 9, 0, 'E9'),
(3, 'E', 10, 0, 'E10'),
(3, 'F', 1, 0, 'F1'),
(3, 'F', 2, 0, 'F2'),
(3, 'F', 3, 0, 'F3'),
(3, 'F', 4, 0, 'F4'),
(3, 'F', 5, 0, 'F5'),
(3, 'F', 6, 0, 'F6'),
(3, 'F', 7, 0, 'F7'),
(3, 'F', 8, 0, 'F8'),
(3, 'F', 9, 0, 'F9'),
(3, 'F', 10, 0, 'F10'),
(3, 'G', 1, 0, 'G1'),
(3, 'G', 2, 0, 'G2'),
(3, 'G', 3, 0, 'G3'),
(3, 'G', 4, 0, 'G4'),
(3, 'G', 5, 0, 'G5'),
(3, 'G', 6, 0, 'G6'),
(3, 'G', 7, 0, 'G7'),
(3, 'G', 8, 0, 'G8'),
(3, 'G', 9, 0, 'G9'),
(3, 'G', 10, 0, 'G10'),
(4, 'A', 1, 0, 'A1'),
(4, 'A', 2, 0, 'A2'),
(4, 'A', 3, 0, 'A3'),
(4, 'A', 4, 0, 'A4'),
(4, 'A', 5, 0, 'A5'),
(4, 'A', 6, 0, 'A6'),
(4, 'A', 7, 0, 'A7'),
(4, 'A', 8, 0, 'A8'),
(4, 'A', 9, 0, 'A9'),
(4, 'A', 10, 0, 'A10'),
(4, 'B', 1, 0, 'B1'),
(4, 'B', 2, 0, 'B2'),
(4, 'B', 3, 0, 'B3'),
(4, 'B', 4, 0, 'B4'),
(4, 'B', 5, 0, 'B5'),
(4, 'B', 6, 0, 'B6'),
(4, 'B', 7, 0, 'B7'),
(4, 'B', 8, 0, 'B8'),
(4, 'B', 9, 0, 'B9'),
(4, 'B', 10, 0, 'B10'),
(4, 'C', 1, 0, 'C1'),
(4, 'C', 2, 0, 'C2'),
(4, 'C', 3, 0, 'C3'),
(4, 'C', 4, 0, 'C4'),
(4, 'C', 5, 0, 'C5'),
(4, 'C', 6, 0, 'C6'),
(4, 'C', 7, 0, 'C7'),
(4, 'C', 8, 0, 'C8'),
(4, 'C', 9, 0, 'C9'),
(4, 'C', 10, 0, 'C10'),
(4, 'D', 1, 0, 'D1'),
(4, 'D', 2, 0, 'D2'),
(4, 'D', 3, 0, 'D3'),
(4, 'D', 4, 0, 'D4'),
(4, 'D', 5, 0, 'D5'),
(4, 'D', 6, 0, 'D6'),
(4, 'D', 7, 0, 'D7'),
(4, 'D', 8, 0, 'D8'),
(4, 'D', 9, 0, 'D9'),
(4, 'D', 10, 0, 'D10'),
(4, 'E', 1, 0, 'E1'),
(4, 'E', 2, 0, 'E2'),
(4, 'E', 3, 0, 'E3'),
(4, 'E', 4, 0, 'E4'),
(4, 'E', 5, 0, 'E5'),
(4, 'E', 6, 0, 'E6'),
(4, 'E', 7, 0, 'E7'),
(4, 'E', 8, 0, 'E8'),
(4, 'E', 9, 0, 'E9'),
(4, 'E', 10, 0, 'E10'),
(4, 'F', 1, 0, 'F1'),
(4, 'F', 2, 0, 'F2'),
(4, 'F', 3, 0, 'F3'),
(4, 'F', 4, 0, 'F4'),
(4, 'F', 5, 0, 'F5'),
(4, 'F', 6, 0, 'F6'),
(4, 'F', 7, 0, 'F7'),
(4, 'F', 8, 0, 'F8'),
(4, 'F', 9, 0, 'F9'),
(4, 'F', 10, 0, 'F10'),
(4, 'G', 1, 0, 'G1'),
(4, 'G', 2, 0, 'G2'),
(4, 'G', 3, 0, 'G3'),
(4, 'G', 4, 0, 'G4'),
(4, 'G', 5, 0, 'G5'),
(4, 'G', 6, 0, 'G6'),
(4, 'G', 7, 0, 'G7'),
(4, 'G', 8, 0, 'G8'),
(4, 'G', 9, 0, 'G9'),
(4, 'G', 10, 0, 'G10');

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
('2', '10', 590000, '4/8/2021', '12'),
('2', '2', 400000, '12/8/2021', '2'),
('11', '4', 200000, '12/9/2021', '3'),
('1', '2', 200000, '2/9/2021', '4'),
('2', '2', 300000, '3/9/2021', '5'),
('1', '6', 100000, '6/9/2021', '6'),
('2', '5', 210000, '6/9/2021', '8'),
('1', '3', 90000, '6/9/2021', '9');

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
('10', 'Trần Tuấn Du', '235436735', 'Nam', 'tuandu@gmail.com', 'tuandu123'),
('2', 'Phan Thi Như', '2444435432', 'Nữ', 'Nhu123@gmail.com', 'Phannhu123'),
('3', 'Nguyễn Văn B', '02030503023', 'Nam', 'fefwfwf@gmail.com', 'phr123'),
('4', 'Sam', '0708978308', 'Nữ', 'hainguyen6701@gmail.com', '53fa048ccddb201659e94fa80df2c4e6'),
('5', 'Nguyễn Tấn Phát', '1234321086', 'Nam', 'PhatTan123@gmail.com', 'Tanphat123'),
('6', 'Bùi Thi Hoa', '29443948593', 'Nữ', 'HoaBui123@gmail.com', 'Hoabui234'),
('8', 'Phan Thi Hoài', '1305968292', 'Nữ', 'hoai123@gmail.com', 'Hoai789'),
('9', 'Lâm Doanh Sâm', '0275867493', 'Nữ', 'samlam@gmail.com', 'samlam123');

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
(1, 10, 10),
(2, 10, 10),
(3, 10, 10),
(4, 10, 10);

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
(1, 'hai230601', 'tuanhai230601', 'Nguyễn Tuấn Hải', 'Admin'),
(2, 'thien12', 'thien1234', 'Huỳnh Chí Thiện', 'Nhân viên'),
(3, 'tin23', 'trungtin324', 'Đoàn Trung Tín', 'Nhân viên'),
(4, 'tuan124', 'anhtuan123', 'Lý Anh Tuấn', 'Nhân viên'),
(5, 'tuananh233', 'anh23334', 'Phạm Tuấn Anh', 'Nhân viên'),
(6, 'uyen1244', 'bauyen123', 'Huỳnh Thị Bá Uyên', 'Nhân viên'),
(7, 'tuyetmai978', 'mai6789', 'Lê Thị Ánh Mai', 'Nhân viên'),
(8, 'ngocyen687', 'ngocyen1', 'Đàm Ngọc Yến', 'Nhân viên'),
(9, 'anhthu123', 'thu1351', 'Trần Ánh Thư', 'Nhân viên'),
(10, 'conganh23', 'anh28492', 'Lê Thị Công Anh', 'Nhân viên'),
(11, 'quythien', 'thien1213', 'Vũ Quý Thiện', 'Nhân viên'),
(12, 'ngocanh', 'ngocanh123', 'Quế Ngọc Ánh', 'Nhân viên'),
(13, 'johny', 'johny123', 'Johny Trần', 'Nhân viên'),
(14, 'congthien', 'thien231', 'Lê Công Thiện', 'Nhân viên'),
(15, 'quoctuan1', 'quoctuan231', 'Nguyễn Quốc Tuấn', 'Nhân viên'),
(16, 'anhtho3', 'anhtho231', 'Nguyễn Ánh Thơ', 'Nhân viên'),
(17, 'hanh28', 'hanh2812', 'Trần Tuyết Hạnh', 'Nhân viên'),
(18, 'queanh212', 'queanh221', 'Mai Quế Anh', 'Nhân viên');

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
  ADD CONSTRAINT `chi_tiet_hoa_don_ibfk_6` FOREIGN KEY (`idPhong`) REFERENCES `phong_chieu` (`idPhong`);

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

package com.university.services;

import com.university.Repository.AdminRepository;
import com.university.dtos.response.AdminDto;
import com.university.entity.Admin;
import com.university.exceptions.ResourceNotFoundException;
import com.university.mapping.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    public AdminDto getAdminDtoById(Long id) {
        logger.info("Fetching admin with id: {}", id);
        return adminMapper.toDto(getAdminById(id));
    }

    public List<AdminDto> getAllAdmins() {
        logger.info("Fetching all admins");

        List<Admin> admins = adminRepository.findAll();
        logger.info("Total admins found: {}", admins.size());

        return adminMapper.toDtoList(admins);
    }

    // helper Methods
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Admin not found with id: {}", id);
                    return new ResourceNotFoundException("Admin not found with id: " + id);
                });
    }

}
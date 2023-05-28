package ua.zakharenko.LibraryManagementSystem.service;


import ua.zakharenko.LibraryManagementSystem.entity.Publisher;

import java.util.List;

public interface PublisherService {

	public List<Publisher> findAllPublishers();

	public Publisher findPublisherById(Long id);

	public void createPublisher(Publisher publisher);

	public void updatePublisher(Publisher publisher);

	public void deletePublisher(Long id);

}

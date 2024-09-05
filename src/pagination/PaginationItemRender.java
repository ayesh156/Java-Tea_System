package pagination;

import javax.swing.JButton;

public interface PaginationItemRender {

    // Method to create a pagination item (button)
    JButton createPaginationItem(Object value, boolean isPrevious, boolean isNext, boolean enable);

    // Method to create a generic button
    JButton createButton(Object value, boolean isPrevious, boolean isNext, boolean enable);

    // Method to create the icon or text for the "Previous" button
    Object createPreviousIcon();

    // Method to create the icon or text for the "Next" button
    Object createNextIcon();

    // New method to create the icon or text for the "First" button
    Object createFirstIcon();

    // New method to create the icon or text for the "Last" button
    Object createLastIcon();
}

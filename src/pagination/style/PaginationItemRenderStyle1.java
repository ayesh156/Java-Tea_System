package pagination.style;

import javax.swing.JButton;
import pagination.DefaultPaginationItemRender;

public class PaginationItemRenderStyle1 extends DefaultPaginationItemRender {

    @Override
    public JButton createButton(Object value, boolean isPrevious, boolean isNext, boolean enable) {
        JButton button = super.createButton(value, isPrevious, isNext, enable);
        button.setUI(new ButtonUI()); // Apply custom UI style to the button

        // Customize the appearance or behavior of the "First" and "Last" buttons if necessary
        if (value.equals("First") || value.equals("Last")) {
            // You can apply specific styles or configurations for "First" and "Last" buttons here
            // For example, you might want to set a different background or foreground color
            button.setBackground(java.awt.Color.LIGHT_GRAY);
            button.setForeground(java.awt.Color.BLACK);
        }

        return button;
    }

    @Override
    public Object createPreviousIcon() {
        return "Previous";
    }

    @Override
    public Object createNextIcon() {
        return "Next";
    }

    // You can also override createButton for more specific customization, if needed
    @Override
    public JButton createPaginationItem(Object value, boolean isPrevious, boolean isNext, boolean enable) {
        JButton button = createButton(value, isPrevious, isNext, enable);
        
        // Additional customizations if necessary
        if (value.equals("First")) {
            button.setText("First");
        } else if (value.equals("Last")) {
            button.setText("Last");
        }

        return button;
    }
}

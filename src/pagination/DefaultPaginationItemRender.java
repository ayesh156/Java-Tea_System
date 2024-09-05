package pagination;

import javax.swing.Icon;
import javax.swing.JButton;

public class DefaultPaginationItemRender implements PaginationItemRender {

    @Override
    public JButton createPaginationItem(Object value, boolean isPrevious, boolean isNext, boolean enable) {
        JButton cmd = createButton(value, isPrevious, isNext, enable);

        if (isPrevious) {
            Object icon = createPreviousIcon();
            if (icon != null) {
                if (icon instanceof Icon) {
                    cmd.setIcon((Icon) icon);
                } else {
                    cmd.setText(icon.toString());
                }
            }
        } else if (isNext) {
            Object icon = createNextIcon();
            if (icon != null) {
                if (icon instanceof Icon) {
                    cmd.setIcon((Icon) icon);
                } else {
                    cmd.setText(icon.toString());
                }
            }
        } else if (value.equals("First")) {
            Object icon = createFirstIcon();
            if (icon != null) {
                if (icon instanceof Icon) {
                    cmd.setIcon((Icon) icon);
                } else {
                    cmd.setText(icon.toString());
                }
            }
        } else if (value.equals("Last")) {
            Object icon = createLastIcon();
            if (icon != null) {
                if (icon instanceof Icon) {
                    cmd.setIcon((Icon) icon);
                } else {
                    cmd.setText(icon.toString());
                }
            }
        } else {
            cmd.setText(value.toString());
        }

        if (!enable) {
            cmd.setFocusable(false);
        }

        return cmd;
    }

    @Override
    public JButton createButton(Object value, boolean isPrevious, boolean isNext, boolean enable) {
        JButton button = new JButton();
        button.setEnabled(enable);
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

    public Object createFirstIcon() {
        return "First";
    }

    public Object createLastIcon() {
        return "Last";
    }
}

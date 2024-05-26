@import de.tschuehly.easy.spring.auth.user.management.table.UserTableComponent.UserTableContext
@import static de.tschuehly.easy.spring.auth.user.UserController.GET_CREATE_USER_MODAL
@import static de.tschuehly.easy.spring.auth.user.management.UserManagementComponent.MODAL_CONTAINER_ID
@import static de.tschuehly.easy.spring.auth.user.management.table.UserTableComponent.USER_TABLE_BODY_ID
@param UserTableContext userTableContext
<table>
    <thead>
    <tr>
        <th>
            uuid
        </th>
        <th>
            username
        </th>
        <th>
            password
        </th>
    </tr>
    </thead>
    <tbody id="${USER_TABLE_BODY_ID}">
    @for(var row: userTableContext.userTableRowList()) <%-- (1) --%>
        ${row} <%-- (2) --%>
    @endfor
    </tbody>
    <tfoot>
    <tr>
        <td colspan="4">
            <button hx-get="${GET_CREATE_USER_MODAL}" hx-target="#${MODAL_CONTAINER_ID}">
                Create new User
            </button>
        </td>
    </tr>
    </tfoot>
</table>